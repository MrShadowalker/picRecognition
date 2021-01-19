package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.*;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

/**
 * 人脸搜索可以根据输入图片，在数据库中搜索相似的人脸图片数据。
 * 操作流程：创建人脸数据库 -> 创建人脸样本 -> 添加人脸数据 -> 搜索人脸
 *
 * @author Shadowalker
 */
public class SearchFaceDemo {

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) throws InterruptedException {
        String dbName = "default";
        String human1_1 = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/human_11.jpg ";
        String human1_2 = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/human_12.jpg ";
        String human2_1 = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/human_21.jpg ";
        String human2_2 = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/human_22.jpg ";
        String sample = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/sample.jpg ";
        String entityId1 = "human1";
        String entityId2 = "human2";
        // 1.创建人脸样本
        addFaceEntity(dbName, entityId1);
        addFaceEntity(dbName, entityId2);
        // 2.向人脸样本中加入人脸，每个样本人脸上限为5
        addFace(dbName, entityId1, human1_1);
        addFace(dbName, entityId1, human1_2);
        addFace(dbName, entityId2, human2_1);
        addFace(dbName, entityId2, human2_2);
        Thread.currentThread().sleep(3000);
        // 3.到人脸库中查找
        searchFace(dbName, sample, 1);
    }

    private static void addFaceEntity(String dbName, String entityId) {
        AddFaceEntityRequest addFaceEntityRequest = new AddFaceEntityRequest();
        addFaceEntityRequest.setDbName(dbName);
        addFaceEntityRequest.setEntityId(entityId);
        try {
            AddFaceEntityResponse addFaceEntityResponse = client.getAcsResponse
                    (addFaceEntityRequest);
            System.out.println("添加人脸样本：");
            System.out.println(new Gson().toJson(addFaceEntityResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 添加人脸数据
     *
     * @param dbName   数据库名称
     * @param entityId 实体ID
     * @param imageUrl 人脸图片地址，必须是同Region的OSS的图片地址。人脸必须是正面无
     *                 遮挡单人人脸。
     */
    private static void addFace(String dbName, String entityId, String imageUrl) {
        AddFaceRequest addFaceRequest = new AddFaceRequest();
        addFaceRequest.setDbName(dbName);
        addFaceRequest.setEntityId(entityId);
        addFaceRequest.setImageUrl(imageUrl);
        try {
            AddFaceResponse addFaceResponse = client.getAcsResponse(addFaceRequest);
            System.out.println("添加人脸数据：");
            System.out.println(new Gson().toJson(addFaceResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 搜索人脸
     *
     * @param dbName   数据库名称
     * @param imageUrl 图片URL地址。必须是同Region的OSS地址。
     */
    private static void searchFace(String dbName, String imageUrl, Integer limit) {
        SearchFaceRequest searchFaceRequest = new SearchFaceRequest();
        searchFaceRequest.setDbName(dbName);
        searchFaceRequest.setImageUrl(imageUrl);
        searchFaceRequest.setLimit(limit);
        try {
            SearchFaceResponse searchFaceResponse = client.getAcsResponse(searchFaceRequest);
            System.out.println("搜索人脸：");
            System.out.println(new Gson().toJson(searchFaceResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}