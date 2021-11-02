package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import com.codingsaint.mediadeck.models.Element;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ElementEndpoints {

    @PostMapping("element")
    Element save(@RequestBody Element element) throws MediaDeckExceptions;

    @PutMapping("element")
    Element update(@RequestBody Element element) throws MediaDeckExceptions;

    @GetMapping("elements")
    List<Element> details() throws MediaDeckExceptions;

    @GetMapping("elements/deck/{id}")
    List<Element> elements(@PathVariable("id") String id) throws MediaDeckExceptions;

    @GetMapping("element/{id}")
    Element getDetail(@PathVariable("id") String id) throws MediaDeckExceptions;

    @DeleteMapping("element/{id}")
    void delete(@PathVariable("id") String id) throws MediaDeckExceptions;
}
