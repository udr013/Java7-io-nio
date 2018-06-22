package com.udr013;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputStreamExample{

	public static void main(String[] args) throws IOException{


		FileInputStream inputStream = new FileInputStream("/home/udr013/bla/Erik Jackson - Rainy Days.flac"); //throws FileNotFoundException thou inputStream.read throws IOException which is higher
		FileInputStream inputStream2 = new FileInputStream("/home/udr013/bla/Erik Jackson - Rainy Days.flac");
		FileInputStream inputStream3 = new FileInputStream("/home/udr013/bla/Erik Jackson - Rainy Days.flac");
		FileOutputStream outputStream = new FileOutputStream("/home/udr013/bla/Erik Jackson - Rainy Days-copy-1.flac");
		FileOutputStream outputStream2 = new FileOutputStream("/home/udr013/bla/Erik Jackson - Rainy Days-copy-2.flac");
		FileOutputStream outputStream3 = new FileOutputStream("/home/udr013/bla/Erik Jackson - Rainy Days-copy-3.flac");

		BufferedInputStream bInputStream = new BufferedInputStream(inputStream3); //throws FileNotFoundException thou inputStream.read throws IOException which is higher
		BufferedOutputStream bOutputStream = new BufferedOutputStream(outputStream3);

		System.out.println(inputStream.getFD());
		System.out.println(outputStream.getFD());

         //bad code here, we check by reading and write the next as its a stream, so we lose half the data
		while (inputStream.read() != -1){
			outputStream.write(inputStream.read());
		}

		/** remember it's a stream, so we need to store the byte in to int data,
		 *  so we can reuse it, otherwise it's lost when we check for -1 and write the next byte
		 *  so the above code is malicious
		 *  **/
		long start = System.currentTimeMillis();
		int data;
		while ((data = inputStream.read()) != -1){ //read and save it, so we can write it before it's gone.
			outputStream.write(data);
		}
		long stop = System.currentTimeMillis();
		System.out.println("time used byte Filestream: " + (stop - start) + "ms");

		/** writing single bytes are costly, we can optimize using byte[] */
		start = System.currentTimeMillis();
		int data1;
		byte[] byteArray = new byte[1024];
		while((data1 = inputStream2.read(byteArray)) != -1){ // only returns the count of bytes! so here data is just a number..
			outputStream2.write(byteArray, 0, data1); // data is the length of the bytearray...

// 			outputStream2.write(data); //this won't work, like in the previous case where we just wrote 1 byte, see comment above
//			outputStream2.write(byteArray); //looks like it works but will not be identical will writes full byteArray.. including empty part, so in the end it's corrupt or you are very lucky
		}
		stop = System.currentTimeMillis();

		System.out.println("time used byte[] FileStream: " + (stop - start) + "ms");

		start = System.currentTimeMillis();
		int data2;
		while((data2 = bInputStream.read(byteArray)) != -1){ // only returns the count of bytes! so here data is just a number..
			bOutputStream.write(byteArray, 0, data2); // data is the length of the bytearray...
		}
		stop = System.currentTimeMillis();

		System.out.println("time used buffered FileStream: " + (stop - start) + "ms");

	}
}
