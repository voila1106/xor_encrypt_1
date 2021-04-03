import java.io.*;
import java.security.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		if(args.length < 2)
		{
			System.out.println("invalid arguments");
			System.out.println("exec filename key");
			return;
		}
		String keystr = args[1];
		int key = (int)Long.parseLong(md5(keystr).substring(0, 8), 16) / 2;
		System.out.println(key);
		File f = new File(args[0]);
		FileInputStream fi = new FileInputStream(f);


		byte[] c = fi.readAllBytes();
//		System.out.println(c.length);
		fi.close();


		for(int i = 0; i < c.length; i++)
		{
			//System.out.println(Integer.toHexString(c[i]));
			c[i] = (byte)(c[i] ^ key);
			//System.out.println(Integer.toHexString(c[i]));
		}
		DataOutputStream os = new DataOutputStream(new FileOutputStream(f));
		os.write(c);
		os.flush();
		os.close();
		System.out.println("Done");

	}

	static String md5(String str) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(str.getBytes());
		StringBuilder sb = new StringBuilder();
		for(byte t : b)
		{
			int u = t;
			if(u < 0)
			{
				u -= 0xffffff00;
			}
			sb.append(Integer.toHexString(u));
		}
		return sb.toString();
	}

}
