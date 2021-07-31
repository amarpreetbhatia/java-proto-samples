package com.amar;

import com.example.tutorial.protos.AddressBook;
import com.example.tutorial.protos.Person;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;

import java.time.Instant;
import java.time.ZonedDateTime;

public class MainPerson {
    public static void main(String[] args) {
        Person.Builder person = Person.newBuilder()
                .setId(401)
                .setName("test")
                .setEmail("a@s.com");
        Person.PhoneNumber phone1 = Person.PhoneNumber
                .newBuilder()
                .setNumber("000101")
                .setType(Person.PhoneType.HOME)
                .build();
        Person.PhoneNumber phone2 = Person.PhoneNumber
                .newBuilder()
                .setNumber("200101")
                .setType(Person.PhoneType.MOBILE)
                .build();
        person.addPhones(phone1);
        person.addPhones(phone2);
        person.setLastUpdated(Timestamp
                .newBuilder()
                .setSeconds(ZonedDateTime.now().toEpochSecond())
                .build());
        AddressBook address = AddressBook.newBuilder().addPeople(person).build();

        try {
            System.out.println("JSON Format -->\n" + JsonFormat.printer().print(address));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
