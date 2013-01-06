package at.ac.tuwien.inso.esse.advancedsecsys.client.transfer;

import javax.xml.bind.DatatypeConverter;



public class Utility
{
  private static String lf = System.getProperty("line.separator");

  public static String b64d(String paramString)
  {
    String str = new String(DatatypeConverter.printBase64Binary(paramString.getBytes()));
    if (str.endsWith(lf))
      str = str.substring(0, str.length() - lf.length());
    return str;
  }

  public static String b64e(String paramString)
  {
    String str = new String(DatatypeConverter.parseBase64Binary(paramString));
    if (str.endsWith(lf))
      str = str.substring(0, str.length() - lf.length());
    return str;
  }
}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.Utility
 * JD-Core Version:    0.6.2
 */