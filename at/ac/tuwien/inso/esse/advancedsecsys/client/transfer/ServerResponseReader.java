package at.ac.tuwien.inso.esse.advancedsecsys.client.transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry;

public class ServerResponseReader
{
	public static List<Entry> interpretQuery(BufferedReader paramBufferedReader)
			throws IOException
			{
		List<Entry> localArrayList = new ArrayList<Entry>();
		int i = 0;
		while (true)
		{
			String line = paramBufferedReader.readLine();
			System.out.println("Received line: " + line);
			if (line == null)
				break;
			if (!line.equals("<query"))
			{
				if (!line.startsWith("<query:"))
					continue;
				String[] arr = line.substring("<query".length()).split(" ");
				localArrayList.add(new Entry(Utility.b64d(arr[0]), Utility.b64d(arr[1])));
				continue;
			}
			i = 1;
		}
		if (i == 0)
			localArrayList = null;
		return (List<Entry>)localArrayList;
			}
}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerResponseReader
 * JD-Core Version:    0.6.2
 */