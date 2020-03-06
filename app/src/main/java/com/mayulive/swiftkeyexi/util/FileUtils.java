package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import androidx.documentfile.provider.DocumentFile;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils
{
	private static String LOGTAG = ExiModule.getLogTag(FileUtils.class);

	private static final int BUFFER = 2048;

	public static boolean copyToDir(File outputDir, File file)
	{
		return copyToDir(outputDir, file, file.getName());
	}

	public static boolean copyToDir(File outputDir, File file, String outputName)
	{

		BufferedInputStream bufferStream = null;
		BufferedOutputStream outputStream = null;

		try
		{
			bufferStream = new BufferedInputStream( new FileInputStream( file ) );

			File outputFile = new File(outputDir, outputName);

			outputStream = new BufferedOutputStream( new FileOutputStream( outputFile) );

			copyStream(bufferStream, outputStream);
		}
		catch (Exception e)
		{
			Log.e(LOGTAG, "Failed to copy file");
			e.printStackTrace();

			return false;
		}
		finally
		{
			if ( outputStream != null)
			{
				try
				{
					outputStream.flush();
					outputStream.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
					return false;
				}
			}

			if ( bufferStream != null)
			{
				try
				{
					bufferStream.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	public static boolean copyToDir(Context context,  DocumentFile outputDir, DocumentFile file)
	{

		BufferedInputStream bufferStream = null;
		BufferedOutputStream outputStream = null;

		try
		{
			bufferStream = new BufferedInputStream( context.getContentResolver().openInputStream(file.getUri()) );

			DocumentFile outputFile = null;

			{
				// Only create if it doesn't already exist
				outputFile = outputDir.findFile(file.getName());
				if ( outputFile == null || !outputFile.exists() )
				{
					outputFile = outputDir.createFile( file.getType(), file.getName() );
				}
			}


			outputStream = new BufferedOutputStream( context.getContentResolver().openOutputStream( outputFile.getUri()) );

			copyStream(bufferStream, outputStream);
		}
		catch (Exception e)
		{
			Log.e(LOGTAG, "Failed to copy file");
			e.printStackTrace();

			return false;
		}
		finally
		{
			if ( outputStream != null)
			{
				try
				{
					outputStream.flush();
					outputStream.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
				}
			}

			if ( bufferStream != null)
			{
				try
				{
					bufferStream.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
				}
			}
		}

		return true;
	}

	public static File generateZip(File[] files, File OutputDirectory, String filenameWithExtension)
	{

		// Get files before creating output file, otherwise we'll be zipping ourselves in an infinite loop
		File zipFile = new File(OutputDirectory, filenameWithExtension);
		ZipOutputStream out = null;

		try
		{
			out = new ZipOutputStream(new BufferedOutputStream( new FileOutputStream(zipFile) ));

			BufferedInputStream fileInputStream = null;


			byte buffer[] = new byte[BUFFER];

			for (File file : files)
			{
				fileInputStream = new BufferedInputStream( new FileInputStream(file), BUFFER);

				ZipEntry entry = new ZipEntry( file.getName() );

				out.putNextEntry(entry);

				copyStream(fileInputStream, out);

				fileInputStream.close();
			}


			out.finish();
			out.close();
		}
		catch (Exception e)
		{
			zipFile = null;
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.flush();
					out.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
				}
			}
		}

		return zipFile;

	}

	public static boolean copyStream(InputStream input, OutputStream output) throws IOException
	{
		byte[] buffer = new byte[BUFFER];

		int read = -1;
		while ( ( read = input.read(buffer) ) != -1)
		{
			output.write(buffer,0,read);
		}

		return true;
	}

	public static boolean Unzip(File outputDir, InputStream backupZip )
	{
		outputDir.mkdirs();

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try
		{
			ZipInputStream zipStream = new ZipInputStream(backupZip);


			ZipEntry entry = null;
			while ( ( entry = zipStream.getNextEntry() ) != null )
			{

				input = new BufferedInputStream( zipStream );

				File newFile = new File( outputDir, entry.getName() );
				newFile.createNewFile();

				output = new BufferedOutputStream( new FileOutputStream( newFile ));

				FileUtils.copyStream(input, output);

				output.flush();
				output.close();

				zipStream.closeEntry();
			}
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
			return false;
		}
		finally
		{
			if ( input != null)
			{
				try
				{
					input.close();
				}
				catch ( Exception ex )
				{
					ex.printStackTrace();
				}
			}
		}

		return true;

	}


}
