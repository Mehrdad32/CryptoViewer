package ir.mehrdad32.cryptoviewer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

public class CryptoViewModel extends ViewModel {
    private MutableLiveData<CryptoPrice> crypto;

    public LiveData<CryptoPrice> getCrypto() {
        if(crypto == null) {
            crypto = new MutableLiveData<>();
        }

        return crypto;
    }

    public void loadCrypto() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://www.megaweb.ir/api/digital-money").build();
            client.setReadTimeout(30000, TimeUnit.MILLISECONDS);
            client.setConnectTimeout(30000, TimeUnit.MILLISECONDS);
            client.setWriteTimeout(30000, TimeUnit.MILLISECONDS);
            Response responses = null;

            responses = client.newCall(request).execute();

            String responseFromServer = responses.body().string();

            Gson gson = new Gson();
            CryptoPrice cryptoPrice = gson.fromJson(responseFromServer, CryptoPrice.class);
            crypto.postValue(cryptoPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
