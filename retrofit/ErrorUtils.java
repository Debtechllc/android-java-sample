package debtechllc.deb.sonderblu.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                RetrofitRequest.getRetrofitInstance()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;
        String jsonOBJECTbody = null;



        try {
            Gson gson=new Gson();
            error = converter.convert(response.errorBody());
           // jsonOBJECTbody = gson.toJson(converter.convert(response.errorBody()));

        } catch (IOException e) {
            return new APIError();
          //  return jsonOBJECTbody;
        }

        return error;
    }
}
