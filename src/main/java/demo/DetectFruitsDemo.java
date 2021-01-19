package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imagerecog.model.v20190930.DetectFruitsRequest;
import com.aliyuncs.imagerecog.model.v20190930.DetectFruitsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

public class DetectFruitsDemo {

    public static void main(String[] args) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        DetectFruitsRequest request = new DetectFruitsRequest();
        request.setRegionId("cn-shanghai");
        request.setImageURL("http://viapi-test.oss-cn-shanghai.aliyuncs.com/%E6%B0%B4%E6%9E%9C%E6%A3%80%E6%B5%8B.jpg");

        try {
            DetectFruitsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}

