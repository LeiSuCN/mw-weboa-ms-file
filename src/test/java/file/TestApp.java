package file;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public class TestApp {
  public static void main(String[] args) throws Exception{
	String stsRegion = "cn-hangzhou";
	String stsVersion = "2015-04-01";
	String roleArn = "acs:ram::1129323400122996:role/mwweboareadrole";
	String roleSessionName = "is_me_001";
	String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	String accessKeyId = "ewgemQjbwxrZguGO";
	String accessKeySecret = "RIzRCzkLUTXo8KhsY7ldZNTzKSEJb8";
	
    String policy = "{\n" +
            "    \"Version\": \"1\", \n" +
            "    \"Statement\": [\n" +
            "        {\n" +
            "            \"Action\": [\n" +
            "                \"oss:GetBucket\", \n" +
            "                \"oss:GetObject\" \n" +
            "            ], \n" +
            "            \"Resource\": [\n" +
            "                \"acs:oss:*:*:mw-weboa,acs:oss:*:*:mw-weboa/*\"\n" +
            "            ], \n" +
            "            \"Effect\": \"Allow\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
	
//	OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	
//	BucketInfo bucket = client.getBucketInfo("mw-weboa");
	
	// client.listBuckets().forEach( item -> System.out.println( item.getName() ) );
//	System.out.println(bucket.getBucket().getLocation());
	
	
	IClientProfile profile = DefaultProfile.getProfile(stsRegion, accessKeyId, accessKeySecret);
	DefaultAcsClient acsClient = new DefaultAcsClient(profile);
	final AssumeRoleRequest request = new AssumeRoleRequest();
	request.setVersion(stsVersion);
	request.setMethod(MethodType.POST);
	request.setProtocol(ProtocolType.HTTPS);
	
	request.setRoleArn(roleArn);
	request.setRoleSessionName(roleSessionName);
	request.setPolicy(policy);
	
	final AssumeRoleResponse response = acsClient.getAcsResponse(request);
	System.out.println(response.getCredentials().getExpiration());
	System.out.println(response.getCredentials().getSecurityToken());
	
//	client.shutdown();
  }
}
