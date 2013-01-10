package at.ac.tuwien.inso.esse.advancedsecsys.client;

import java.security.Security;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import at.ac.tuwien.inso.esse.advancedsecsys.client.dto.Entry;
import at.ac.tuwien.inso.esse.advancedsecsys.client.transfer.ServerBackend;

public class Main {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());

		ServerBackend localServerBackend = new ServerBackend();
		try {
			localServerBackend.connect("127.0.0.1", 8080);
			List<Entry> entries = (List<Entry>) localServerBackend.queryForName("", "sdfsdf");
			if ((entries != null) && (entries.size() > 0)) {
				for (Entry e : entries) {
					System.out.println(e.toString());
				}
			}
			else {
				System.out.println("No entries received");
			}
			localServerBackend.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/* Location:           secure-client-dex2jar.jar
 * Qualified Name:     at.ac.tuwien.inso.esse.advancedsecsys.client.Main
 * JD-Core Version:    0.6.2
 */