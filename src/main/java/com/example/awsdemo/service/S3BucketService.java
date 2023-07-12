package com.example.awsdemo.service;

import com.amazonaws.services.s3.model.*;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import com.example.awsdemo.service.S3BucketService;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class S3BucketService {

    @Autowired
    private final AmazonS3 amazonS3Client;
    public List<Bucket> listBuckets(){

        return amazonS3Client.listBuckets();
    }

    public List<S3ObjectSummary> listObjects(String bucketName){
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }
}
