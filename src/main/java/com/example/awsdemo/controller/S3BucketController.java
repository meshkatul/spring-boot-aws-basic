package com.example.awsdemo.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.awsdemo.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("buckets")
@RequiredArgsConstructor
public class S3BucketController {

    @Autowired
    public final S3BucketService s3BucketService;

    @GetMapping //http://localhost:8080/buckets
    public List<String> listBuckets(){
        List<Bucket> buckets = s3BucketService.listBuckets();
        List<String> names = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        return names;
    }

    @GetMapping(value = "/{bucketName}/objects") //http://localhost:8080/for-learning/objects
    public List<String> listObjects(@PathVariable String bucketName) {
        return s3BucketService.listObjects(bucketName).stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }
}
