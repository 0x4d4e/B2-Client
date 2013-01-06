package at.ac.tuwien.inso.esse.advancedsecsys.client;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerBackend;

public class Main
{
  public static final String EXTRA_AUTH_KEY = "at.ac.tuwien.inso.esse.advancedsecsys.client.AUTH_KEY";
  private static final int SHOW_ENTRIES_REQUEST = 1001;

  /*
  public void login(View paramView)
  {
    ServerBackend localServerBackend = new ServerBackend();
    try
    {
      EditText localEditText = (EditText)findViewById(2131099650);
      String str = localEditText.getText().toString().replaceAll("[\\W&&[^\\\\]]+", "");
      localServerBackend.connect("10.0.2.2", 8080);
      
      if ((localCollection != null) && (localCollection.size() > 0))
      {
        Intent localIntent = new Intent(this, ShowEntriesActivity.class);
        localIntent.putExtra("at.ac.tuwien.inso.esse.advancedsecsys.client.AUTH_KEY", str);
        startActivityForResult(localIntent, 1001);
      }
      else
      {
        localEditText.setText("");
        Toast.makeText(getApplicationContext(), "Wrong authentication key!", 1).show();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    catch (Exception localException)
    {
      Toast.makeText(getApplicationContext(), "Exception has occured! Check debug log: " + localException.getMessage(), 1).show();
      localException.printStackTrace();
    }
  }
  */
  
  public static String ID = "b2c22c650";
  
  public static void listProviders() {
	  StringBuffer sb = new StringBuffer();  
      Provider[] p = Security.getProviders();  
      for (int i = 0; i < p.length; i++) {  
        sb.append("\nProvider : " + p[i].toString() + "\n");  
        Set s = p[i].keySet();  
        Object[] o = s.toArray();  
        Arrays.sort(o);  
        for (int j = 1; j < o.length; j++) {  
          sb.append(o[j].toString() + ", ");  
        }  
      }  
      System.out.println(sb.toString());  
  }

  public static void main(String[] args) {
	  
	  listProviders();
	  
	  ServerBackend localServerBackend = new ServerBackend();
	  try {
		  localServerBackend.connect("192.168.30.101", 8080);
		  Collection localCollection = localServerBackend.queryForName("", ID);
	      localServerBackend.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.Main
 * JD-Core Version:    0.6.2
 */