package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.objectdet.model.v20191230.DetectObjectRequest;
import com.aliyuncs.objectdet.model.v20191230.DetectObjectResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

public class DetectObjectDemo {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        DetectObjectRequest request = new DetectObjectRequest();
        request.setRegionId("cn-shanghai");
        request.setImageURL("http://viapi-test.oss-cn-shanghai.aliyuncs.com/DetectObject.png");

        try {
            DetectObjectResponse response = client.getAcsResponse(request);
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

