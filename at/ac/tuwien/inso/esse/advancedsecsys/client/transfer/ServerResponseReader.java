package at.ac.tuwien.inso.esse.advancedsecsys.client.transfer;

import at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServerResponseReader
{
  public static List<Entry> interpretQuery(BufferedReader paramBufferedReader)
    throws IOException
  {
    Logger.getLogger("ESSE");
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      String str = paramBufferedReader.readLine();
      int i = 0;
      if (str != null)
      {
        if (str.equals("<query"))
          i = 1;
      }
      else
      {
        if (i == 0)
          localArrayList = null;
        return localArrayList;
      }
      if (str.startsWith("<query:"))
      {
        String[] arrayOfString = str.substring("<query".length()).split(" ");
        localArrayList.add(new Entry(Utility.b64d(arrayOfString[0]), Utility.b64d(arrayOfString[1])));
      }
    }
  }
}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerResponseReader
 * JD-Core Version:    0.6.2
 */