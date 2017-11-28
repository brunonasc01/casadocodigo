package br.com.casadocodigo.loja.infra;

import javax.enterprise.inject.Produces;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AmazonS3Producer {

	@Produces
	public AmazonS3 s3(){
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ2RWPGONXCAPIKAA",
				"loQUtFf3ARqfsusge9svZIN3pXUkzk7x3AsY+bvh");

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().
				withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("sa-east-1").build();

		return s3Client;
	}

}
