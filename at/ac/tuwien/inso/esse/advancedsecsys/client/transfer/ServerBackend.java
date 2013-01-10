package at.ac.tuwien.inso.esse.advancedsecsys.client.transfer;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import at.ac.tuwien.inso.esse.advancedsecsys.client.IServerBackend;
import at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry;


public class ServerBackend
  implements IServerBackend
{
  private static SecureRandom secureRandom;
  private static SSLContext sslContext;
  private PrintWriter pWriter;
  private BufferedReader reader;
  private SSLSocket s;

  public void close()
    throws IOException
  {
    this.pWriter.close();
    this.reader.close();
    this.s.close();
  }

  public void connect(String paramString, int paramInt) throws Exception
  {
    X509TrustManager local1 = null;
    KeyStore localKeyStore = null;
    InputStream localInputStream = null;
    if (sslContext == null)
    {
      sslContext = SSLContext.getInstance("TLS");
      local1 = new X509TrustManager()
      {
        public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
          return null;
        }
      };
      localKeyStore = KeyStore.getInstance("BKS", BouncyCastleProvider.PROVIDER_NAME);
      localInputStream = new FileInputStream("androidplatform.bks");
    }
    try
    {
      localKeyStore.load(localInputStream, "123456".toCharArray());
      
      localInputStream.close();
      //String algo = KeyManagerFactory.getDefaultAlgorithm();
      KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance("SunX509");
      localKeyManagerFactory.init(localKeyStore, "password".toCharArray());
      if (secureRandom == null)
        secureRandom = SecureRandom.getInstance("SHA1PRNG");
      sslContext.init(localKeyManagerFactory.getKeyManagers(), new TrustManager[] { local1 }, secureRandom);
      this.s = ((SSLSocket)sslContext.getSocketFactory().createSocket(paramString, paramInt));
      this.pWriter = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
      this.reader = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
      return;
    } catch (Exception e) {
    	System.out.println("Exception: " + e);
    	throw e;
    }
    finally
    {
      localInputStream.close();
    }
  }

  public Collection<Entry> queryForName(String paramString1, String paramString2)
    throws IOException
  {
    this.pWriter.println(">query:" + Utility.b64e(paramString1) + " " + Utility.b64e(paramString2));
    this.pWriter.flush();
    return ServerResponseReader.interpretQuery(this.reader);
  }
}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerBackend
 * JD-Core Version:    0.6.2
 */