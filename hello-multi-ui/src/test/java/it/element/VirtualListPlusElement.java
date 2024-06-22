package it.element;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.flow.component.virtuallist.testbench.VirtualListElement;
import com.vaadin.testbench.TestBenchElement;

import java.util.List;

public class VirtualListPlusElement extends VirtualListElement {

    /**
     * Gets the rows (present in the DOM) specified by the lower and upper row
     * indexes.
     *
     * @param firstRowIndex
     *            the lower row index to be retrieved (inclusive)
     * @param lastRowIndex
     *            the upper row index to be retrieved (inclusive)
     * @return a {@link DivElement} list with the rows contained between the
     *         given coordinates.
     * @throws IndexOutOfBoundsException
     *             if either of the provided row indexes do not exist
     */
    public List<DivElement> getRows(int firstRowIndex, int lastRowIndex)
            throws IndexOutOfBoundsException {
        var rowCount = getRowCount();
        if ((firstRowIndex < 0) || (firstRowIndex > lastRowIndex) || (lastRowIndex >= rowCount)) {
            throw new IndexOutOfBoundsException(
                    "firstRowIndex and lastRowIndex: expected to be [0.."
                            + (rowCount - 1) + "] but were [" + firstRowIndex
                            + ".." + lastRowIndex + "]");
        }

        var script = """
            var virtualList = arguments[0];
            var firstRowIndex = arguments[1];
            var lastRowIndex = arguments[2];

            return Array.from(virtualList.children)
                .filter((item) => !item.hidden)
                .sort((a, b) => a.__virtualIndex - b.__virtualIndex)
                .filter((row) =>
                    (row.__virtualIndex >= firstRowIndex) &&
                    (row.__virtualIndex <= lastRowIndex));
        """;
        var result = executeScript(script, this,
                (Integer) firstRowIndex, (Integer) lastRowIndex);
        if (!(result instanceof List<?> rows)) {
            return List.of();
        }

        return rows.stream()
                .filter(TestBenchElement.class::isInstance)
                .map(TestBenchElement.class::cast)
                .map(testBenchElement -> testBenchElement.wrap(DivElement.class))
                .toList();
    }

    /**
     * Gets the <code>div</code> element for the given row index.
     *
     * @param rowIndex
     *            the row index
     * @return the div element for the row
     * @throws IndexOutOfBoundsException
     *             if no row with given index exists
     */
    public DivElement getRow(int rowIndex) throws IndexOutOfBoundsException {
        var rows = getRows(rowIndex, rowIndex);
        return (rows.size() == 1) ? rows.getFirst() : null;
    }

    /**
     * Gets all the currently visible rows.
     *
     * @return a {@link DivElement} list representing the currently visible
     *         rows.
     */
    public List<DivElement> getVisibleRows() {
        return getRows(getFirstVisibleRowIndex(), getLastVisibleRowIndex());
    }
}
