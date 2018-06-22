package com.udr013;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataInputStreamExample{

	public static void main(String[] args) throws IOException{
		File file  = new File("mydata.data");
		file.createNewFile();
		try(

				FileOutputStream fileOutputStream = new FileOutputStream(file);
				DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
				FileInputStream fileInputStream = new FileInputStream(file);
				DataInputStream dataInputStream = new DataInputStream(fileInputStream);
				)
		{
			dataOutputStream.writeByte(2);
			dataOutputStream.writeBoolean(true);
			dataOutputStream.writeChar('e');
			dataOutputStream.writeChars("chars stuff");
			dataOutputStream.writeDouble(2.0);
			dataOutputStream.writeUTF("UTF some stuff");
			dataOutputStream.writeBytes("byTes");

			//the above output is the input "mydata.data"
			System.out.println(dataInputStream.readByte());
			System.out.println(dataInputStream.readBoolean());
			System.out.println(dataInputStream.readChar());
			int x = 11;
			while(x != 0){ // no readchars so we need to read them one at the time
				System.out.println(dataInputStream.readChar());
				x--;
			}
			System.out.println(dataInputStream.readDouble());
			System.out.println(dataInputStream.readUTF());
			System.out.println((char)dataInputStream.readByte()); //what diff readByte vs read()
			System.out.println((char)dataInputStream.readByte()); //cast otherwise a number
			System.out.println((char)dataInputStream.readByte());
			System.out.println((char)dataInputStream.read());
			System.out.println((char)dataInputStream.read());

		}
	}
}
