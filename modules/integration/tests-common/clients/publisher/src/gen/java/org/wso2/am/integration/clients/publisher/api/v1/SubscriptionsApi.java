/*
 * WSO2 API Manager - Publisher API
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Publisher**.  # Authentication The Publisher REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_publisher\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_publisher\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for publisher REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:api_view apim:api_create\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:api_create apim:api_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9) 
 *
 * The version of the OpenAPI document: v3
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.publisher.api.v1;

import org.wso2.am.integration.clients.publisher.api.ApiCallback;
import org.wso2.am.integration.clients.publisher.api.ApiClient;
import org.wso2.am.integration.clients.publisher.api.ApiException;
import org.wso2.am.integration.clients.publisher.api.ApiResponse;
import org.wso2.am.integration.clients.publisher.api.Configuration;
import org.wso2.am.integration.clients.publisher.api.Pair;
import org.wso2.am.integration.clients.publisher.api.ProgressRequestBody;
import org.wso2.am.integration.clients.publisher.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.publisher.api.v1.dto.ErrorDTO;
import org.wso2.am.integration.clients.publisher.api.v1.dto.SubscriptionListDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionsApi {
    private ApiClient localVarApiClient;

    public SubscriptionsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SubscriptionsApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for blockSubscription
     * @param subscriptionId Subscription Id  (required)
     * @param blockState Subscription block state.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was blocked successfully.  </td><td>  * ETag - Entity Tag of the blocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been blocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call blockSubscriptionCall(String subscriptionId, String blockState, String ifMatch, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/subscriptions/block-subscription";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (subscriptionId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("subscriptionId", subscriptionId));
        }

        if (blockState != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("blockState", blockState));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifMatch != null) {
            localVarHeaderParams.put("If-Match", localVarApiClient.parameterToString(ifMatch));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call blockSubscriptionValidateBeforeCall(String subscriptionId, String blockState, String ifMatch, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'subscriptionId' is set
        if (subscriptionId == null) {
            throw new ApiException("Missing the required parameter 'subscriptionId' when calling blockSubscription(Async)");
        }
        
        // verify the required parameter 'blockState' is set
        if (blockState == null) {
            throw new ApiException("Missing the required parameter 'blockState' when calling blockSubscription(Async)");
        }
        

        okhttp3.Call localVarCall = blockSubscriptionCall(subscriptionId, blockState, ifMatch, _callback);
        return localVarCall;

    }

    /**
     * Block a Subscription
     * This operation can be used to block a subscription. Along with the request, &#x60;blockState&#x60; must be specified as a query parameter.  1. &#x60;BLOCKED&#x60; : Subscription is completely blocked for both Production and Sandbox environments. 2. &#x60;PROD_ONLY_BLOCKED&#x60; : Subscription is blocked for Production environment only. 
     * @param subscriptionId Subscription Id  (required)
     * @param blockState Subscription block state.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was blocked successfully.  </td><td>  * ETag - Entity Tag of the blocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been blocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public void blockSubscription(String subscriptionId, String blockState, String ifMatch) throws ApiException {
        blockSubscriptionWithHttpInfo(subscriptionId, blockState, ifMatch);
    }

    /**
     * Block a Subscription
     * This operation can be used to block a subscription. Along with the request, &#x60;blockState&#x60; must be specified as a query parameter.  1. &#x60;BLOCKED&#x60; : Subscription is completely blocked for both Production and Sandbox environments. 2. &#x60;PROD_ONLY_BLOCKED&#x60; : Subscription is blocked for Production environment only. 
     * @param subscriptionId Subscription Id  (required)
     * @param blockState Subscription block state.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was blocked successfully.  </td><td>  * ETag - Entity Tag of the blocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been blocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> blockSubscriptionWithHttpInfo(String subscriptionId, String blockState, String ifMatch) throws ApiException {
        okhttp3.Call localVarCall = blockSubscriptionValidateBeforeCall(subscriptionId, blockState, ifMatch, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Block a Subscription (asynchronously)
     * This operation can be used to block a subscription. Along with the request, &#x60;blockState&#x60; must be specified as a query parameter.  1. &#x60;BLOCKED&#x60; : Subscription is completely blocked for both Production and Sandbox environments. 2. &#x60;PROD_ONLY_BLOCKED&#x60; : Subscription is blocked for Production environment only. 
     * @param subscriptionId Subscription Id  (required)
     * @param blockState Subscription block state.  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was blocked successfully.  </td><td>  * ETag - Entity Tag of the blocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been blocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call blockSubscriptionAsync(String subscriptionId, String blockState, String ifMatch, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = blockSubscriptionValidateBeforeCall(subscriptionId, blockState, ifMatch, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSubscriptions
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API I. Should be formatted as **provider-name-version**.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource.  (optional)
     * @param query Keywords to filter subscriptions  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription list returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubscriptionsCall(String apiId, Integer limit, Integer offset, String ifNoneMatch, String query, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/subscriptions";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (apiId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("apiId", apiId));
        }

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (query != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("query", query));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifNoneMatch != null) {
            localVarHeaderParams.put("If-None-Match", localVarApiClient.parameterToString(ifNoneMatch));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getSubscriptionsValidateBeforeCall(String apiId, Integer limit, Integer offset, String ifNoneMatch, String query, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getSubscriptionsCall(apiId, limit, offset, ifNoneMatch, query, _callback);
        return localVarCall;

    }

    /**
     * Get all Subscriptions
     * This operation can be used to retrieve a list of subscriptions of the user associated with the provided access token. This operation is capable of  1. Retrieving all subscriptions for the user&#39;s APIs. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions&#x60;  2. Retrieving subscriptions for a specific API. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions?apiId&#x3D;c43a325c-260b-4302-81cb-768eafaa3aed&#x60;
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API I. Should be formatted as **provider-name-version**.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource.  (optional)
     * @param query Keywords to filter subscriptions  (optional)
     * @return SubscriptionListDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription list returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public SubscriptionListDTO getSubscriptions(String apiId, Integer limit, Integer offset, String ifNoneMatch, String query) throws ApiException {
        ApiResponse<SubscriptionListDTO> localVarResp = getSubscriptionsWithHttpInfo(apiId, limit, offset, ifNoneMatch, query);
        return localVarResp.getData();
    }

    /**
     * Get all Subscriptions
     * This operation can be used to retrieve a list of subscriptions of the user associated with the provided access token. This operation is capable of  1. Retrieving all subscriptions for the user&#39;s APIs. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions&#x60;  2. Retrieving subscriptions for a specific API. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions?apiId&#x3D;c43a325c-260b-4302-81cb-768eafaa3aed&#x60;
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API I. Should be formatted as **provider-name-version**.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource.  (optional)
     * @param query Keywords to filter subscriptions  (optional)
     * @return ApiResponse&lt;SubscriptionListDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription list returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SubscriptionListDTO> getSubscriptionsWithHttpInfo(String apiId, Integer limit, Integer offset, String ifNoneMatch, String query) throws ApiException {
        okhttp3.Call localVarCall = getSubscriptionsValidateBeforeCall(apiId, limit, offset, ifNoneMatch, query, null);
        Type localVarReturnType = new TypeToken<SubscriptionListDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get all Subscriptions (asynchronously)
     * This operation can be used to retrieve a list of subscriptions of the user associated with the provided access token. This operation is capable of  1. Retrieving all subscriptions for the user&#39;s APIs. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions&#x60;  2. Retrieving subscriptions for a specific API. &#x60;GET https://127.0.0.1:9443/api/am/publisher/v4/subscriptions?apiId&#x3D;c43a325c-260b-4302-81cb-768eafaa3aed&#x60;
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API I. Should be formatted as **provider-name-version**.  (optional)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource.  (optional)
     * @param query Keywords to filter subscriptions  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription list returned.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubscriptionsAsync(String apiId, Integer limit, Integer offset, String ifNoneMatch, String query, final ApiCallback<SubscriptionListDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSubscriptionsValidateBeforeCall(apiId, limit, offset, ifNoneMatch, query, _callback);
        Type localVarReturnType = new TypeToken<SubscriptionListDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for unBlockSubscription
     * @param subscriptionId Subscription Id  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was unblocked successfully.  </td><td>  * ETag - Entity Tag of the unblocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been unblocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call unBlockSubscriptionCall(String subscriptionId, String ifMatch, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/subscriptions/unblock-subscription";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (subscriptionId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("subscriptionId", subscriptionId));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifMatch != null) {
            localVarHeaderParams.put("If-Match", localVarApiClient.parameterToString(ifMatch));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call unBlockSubscriptionValidateBeforeCall(String subscriptionId, String ifMatch, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'subscriptionId' is set
        if (subscriptionId == null) {
            throw new ApiException("Missing the required parameter 'subscriptionId' when calling unBlockSubscription(Async)");
        }
        

        okhttp3.Call localVarCall = unBlockSubscriptionCall(subscriptionId, ifMatch, _callback);
        return localVarCall;

    }

    /**
     * Unblock a Subscription
     * This operation can be used to unblock a subscription specifying the subscription Id. The subscription will be fully unblocked after performing this operation. 
     * @param subscriptionId Subscription Id  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was unblocked successfully.  </td><td>  * ETag - Entity Tag of the unblocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been unblocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public void unBlockSubscription(String subscriptionId, String ifMatch) throws ApiException {
        unBlockSubscriptionWithHttpInfo(subscriptionId, ifMatch);
    }

    /**
     * Unblock a Subscription
     * This operation can be used to unblock a subscription specifying the subscription Id. The subscription will be fully unblocked after performing this operation. 
     * @param subscriptionId Subscription Id  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was unblocked successfully.  </td><td>  * ETag - Entity Tag of the unblocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been unblocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> unBlockSubscriptionWithHttpInfo(String subscriptionId, String ifMatch) throws ApiException {
        okhttp3.Call localVarCall = unBlockSubscriptionValidateBeforeCall(subscriptionId, ifMatch, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Unblock a Subscription (asynchronously)
     * This operation can be used to unblock a subscription specifying the subscription Id. The subscription will be fully unblocked after performing this operation. 
     * @param subscriptionId Subscription Id  (required)
     * @param ifMatch Validator for conditional requests; based on ETag.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Subscription was unblocked successfully.  </td><td>  * ETag - Entity Tag of the unblocked subscription. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the subscription has been unblocked. Used by caches, or in conditional requests (Will be supported in future).  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call unBlockSubscriptionAsync(String subscriptionId, String ifMatch, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = unBlockSubscriptionValidateBeforeCall(subscriptionId, ifMatch, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
