package com.example.hospital.parser;

public interface Parser<T> {
    T parse(String str);
}
