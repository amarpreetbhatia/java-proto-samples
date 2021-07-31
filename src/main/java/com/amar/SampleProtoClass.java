package com.amar;

import com.amar.proto.SimpleMessageOuterClass.SimpleMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleProtoClass {
    public static void main(String[] args) {
        System.out.println("Hello World");
        SimpleMessage builder = SimpleMessage
                .newBuilder()
                .setMessageId(101)
                .setMessageData("Test")
                .setIsVerified(true)
                .addListOfUsers("U1")
                .addListOfUsers("U2")
//               .setListOfUsers(1,"U1")
//               .setListOfUsers(0,"U2")
                .build();

        System.out.println(builder.toString());
        try {
            FileOutputStream safeToFile = new FileOutputStream("test.bin");
            builder.writeTo(safeToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.out.println("Reading from File");
        try {
            FileInputStream safeToFile = new FileInputStream("test.bin");
            safeToFile.transferTo(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("\nPrinting in JSON format");
        try {
            String jsonString = JsonFormat.printer().print(builder);
            System.out.println(jsonString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
