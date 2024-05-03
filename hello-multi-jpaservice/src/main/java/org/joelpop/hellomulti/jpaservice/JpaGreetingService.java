package org.joelpop.hellomulti.jpaservice;

import org.joelpop.hellomulti.jpaclient.repo.GreetingRepository;
import org.joelpop.hellomulti.jpaservice.map.GreetingMap;
import org.joelpop.hellomulti.jpaservice.map.MapUtil;
import org.joelpop.hellomulti.uimodel.service.GreetingService;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaGreetingService implements GreetingService {

    private final GreetingRepository greetingRepository;

    public JpaGreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Greeting generateAndLog(String name) {
        var greeting = new Greeting();
        greeting.setName(name);
        greeting.setMessage("Hello, " + (name.isEmpty() ? "World" : name) + "!");

        var greetingDetailDto = greetingRepository.saveAndFlush(GreetingMap.map(greeting));

        return GreetingMap.map(greetingDetailDto);
    }

    @Override
    public List<Greeting> fetchAll() {
        return MapUtil.map(greetingRepository.findAllByOrderByTimestampDesc(), GreetingMap::map);
    }

    @Override
    public void delete(Greeting greeting) {
        greetingRepository.delete(GreetingMap.map(greeting));
    }

    @Override
    public void clear() {
        greetingRepository.deleteAll();
    }
}
