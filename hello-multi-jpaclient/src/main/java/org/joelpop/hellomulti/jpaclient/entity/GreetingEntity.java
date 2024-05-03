package org.joelpop.hellomulti.jpaclient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.joelpop.hellomulti.jpaclient.dto.GreetingDetailDto;
import org.joelpop.hellomulti.jpaclient.dto.GreetingSummaryDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "greeting")
public class GreetingEntity extends LongKeyEntity
        implements GreetingDetailDto, GreetingSummaryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "greeting_key")
    private Long key;

    @Column(name = "name_txt", length = 50)
    private String name;

    @Column(name = "message_txt", length = 100)
    private String message;

    @Column(name = "create_timestamp")
    @CreatedDate
    private Instant timestamp;


    @Override
    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public final boolean equals(Object that) {
        if (that == this) {
            return true;
        }

        if (!(that instanceof GreetingEntity greetingEntity)) {
            return false;
        }

        return Objects.equals(key, greetingEntity.key) &&
                Objects.equals(name, greetingEntity.name) &&
                Objects.equals(message, greetingEntity.message) &&
                Objects.equals(timestamp, greetingEntity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, message, timestamp);
    }

    @Override
    public String toString() {
        return "GreetingEntity[" +
                "key:" + key + "," +
                "name:" + name + "," +
                "message:" + message + "," +
                "timestamp:" + timestamp + ']';
    }
}
