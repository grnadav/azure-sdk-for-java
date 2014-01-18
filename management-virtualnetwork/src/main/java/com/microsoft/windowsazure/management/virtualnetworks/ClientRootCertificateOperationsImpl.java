/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.virtualnetworks;

import com.microsoft.windowsazure.core.ServiceOperations;
import com.microsoft.windowsazure.core.pipeline.apache.CustomHttpDelete;
import com.microsoft.windowsazure.core.utils.StreamUtils;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.virtualnetworks.models.ClientRootCertificateCreateParameters;
import com.microsoft.windowsazure.management.virtualnetworks.models.ClientRootCertificateGetResponse;
import com.microsoft.windowsazure.management.virtualnetworks.models.ClientRootCertificateListResponse;
import com.microsoft.windowsazure.management.virtualnetworks.models.GatewayOperationResponse;
import com.microsoft.windowsazure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ClientRootCertificateOperationsImpl implements ServiceOperations<VirtualNetworkManagementClientImpl>, ClientRootCertificateOperations
{
    /**
    * Initializes a new instance of the ClientRootCertificateOperationsImpl
    * class.
    *
    * @param client Reference to the service client.
    */
    ClientRootCertificateOperationsImpl(VirtualNetworkManagementClientImpl client)
    {
        this.client = client;
    }
    
    private VirtualNetworkManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.virtualnetworks.VirtualNetworkManagementClientImpl.
    */
    public VirtualNetworkManagementClientImpl getClient()
    {
        return this.client;
    }
    
    /**
    * The Upload Client Root Certificate operation is used to upload a new
    * client root certificate to Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205129.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param parameters Parameters supplied to the Upload client certificate
    * Virtual Network Gateway operation.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<GatewayOperationResponse> createAsync(final String virtualNetworkName, final ClientRootCertificateCreateParameters parameters)
    {
        return this.getClient().getExecutorService().submit(new Callable<GatewayOperationResponse>() { 
            @Override
            public GatewayOperationResponse call() throws Exception
            {
                return create(virtualNetworkName, parameters);
            }
         });
    }
    
    /**
    * The Upload Client Root Certificate operation is used to upload a new
    * client root certificate to Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205129.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param parameters Parameters supplied to the Upload client certificate
    * Virtual Network Gateway operation.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public GatewayOperationResponse create(String virtualNetworkName, ClientRootCertificateCreateParameters parameters) throws UnsupportedEncodingException, IOException, ServiceException, ParserConfigurationException, SAXException, InterruptedException, ExecutionException, ServiceException
    {
        // Validate
        if (virtualNetworkName == null)
        {
            throw new NullPointerException("virtualNetworkName");
        }
        if (parameters == null)
        {
            throw new NullPointerException("parameters");
        }
        if (parameters.getCertificate() == null)
        {
            throw new NullPointerException("parameters.Certificate");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("virtualNetworkName", virtualNetworkName);
            tracingParameters.put("parameters", parameters);
            CloudTracing.enter(invocationId, this, "createAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/networking/" + virtualNetworkName + "/gateway/clientrootcertificates";
        
        // Create HTTP transport objects
        HttpPost httpRequest = new HttpPost(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2013-11-01");
        
        // Serialize Request
        String requestContent = null;
        requestContent = parameters.getCertificate();
        StringEntity entity = new StringEntity(requestContent);
        httpRequest.setEntity(entity);
        httpRequest.setHeader("Content-Type", "application/xml");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_ACCEPTED)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, requestContent, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        GatewayOperationResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new GatewayOperationResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("GatewayOperationAsyncResponse");
        Element gatewayOperationAsyncResponseElement = elements.getLength() > 0 ? ((Element) elements.item(0)) : null;
        if (gatewayOperationAsyncResponseElement != null)
        {
            NodeList elements2 = gatewayOperationAsyncResponseElement.getElementsByTagName("ID");
            Element idElement = elements2.getLength() > 0 ? ((Element) elements2.item(0)) : null;
            if (idElement != null)
            {
                String idInstance;
                idInstance = idElement.getTextContent();
                result.setOperationId(idInstance);
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * The Delete Client Root Certificate operation deletes a previously
    * uploaded client root certificate. from Windows Azure  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205128.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param certificateThumbprint The X509 certificate thumbprint.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<GatewayOperationResponse> deleteAsync(final String virtualNetworkName, final String certificateThumbprint)
    {
        return this.getClient().getExecutorService().submit(new Callable<GatewayOperationResponse>() { 
            @Override
            public GatewayOperationResponse call() throws Exception
            {
                return delete(virtualNetworkName, certificateThumbprint);
            }
         });
    }
    
    /**
    * The Delete Client Root Certificate operation deletes a previously
    * uploaded client root certificate. from Windows Azure  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205128.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param certificateThumbprint The X509 certificate thumbprint.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public GatewayOperationResponse delete(String virtualNetworkName, String certificateThumbprint) throws IOException, ServiceException, ParserConfigurationException, SAXException, InterruptedException, ExecutionException, ServiceException
    {
        // Validate
        if (virtualNetworkName == null)
        {
            throw new NullPointerException("virtualNetworkName");
        }
        if (certificateThumbprint == null)
        {
            throw new NullPointerException("certificateThumbprint");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("virtualNetworkName", virtualNetworkName);
            tracingParameters.put("certificateThumbprint", certificateThumbprint);
            CloudTracing.enter(invocationId, this, "deleteAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/networking/" + virtualNetworkName + "/gateway/clientrootcertificates/" + certificateThumbprint;
        
        // Create HTTP transport objects
        CustomHttpDelete httpRequest = new CustomHttpDelete(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2013-11-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        GatewayOperationResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new GatewayOperationResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("GatewayOperationAsyncResponse");
        Element gatewayOperationAsyncResponseElement = elements.getLength() > 0 ? ((Element) elements.item(0)) : null;
        if (gatewayOperationAsyncResponseElement != null)
        {
            NodeList elements2 = gatewayOperationAsyncResponseElement.getElementsByTagName("ID");
            Element idElement = elements2.getLength() > 0 ? ((Element) elements2.item(0)) : null;
            if (idElement != null)
            {
                String idInstance;
                idInstance = idElement.getTextContent();
                result.setOperationId(idInstance);
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * The Get Client Root Certificate operation returns the public portion of a
    * previously uploaded client root certificate in a base-64 encoded format
    * from Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205127.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param certificateThumbprint The X509 certificate thumbprint.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<ClientRootCertificateGetResponse> getAsync(final String virtualNetworkName, final String certificateThumbprint)
    {
        return this.getClient().getExecutorService().submit(new Callable<ClientRootCertificateGetResponse>() { 
            @Override
            public ClientRootCertificateGetResponse call() throws Exception
            {
                return get(virtualNetworkName, certificateThumbprint);
            }
         });
    }
    
    /**
    * The Get Client Root Certificate operation returns the public portion of a
    * previously uploaded client root certificate in a base-64 encoded format
    * from Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205127.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @param certificateThumbprint The X509 certificate thumbprint.
    * @return A standard storage response including an HTTP status code and
    * request ID.
    */
    @Override
    public ClientRootCertificateGetResponse get(String virtualNetworkName, String certificateThumbprint) throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        if (virtualNetworkName == null)
        {
            throw new NullPointerException("virtualNetworkName");
        }
        if (certificateThumbprint == null)
        {
            throw new NullPointerException("certificateThumbprint");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("virtualNetworkName", virtualNetworkName);
            tracingParameters.put("certificateThumbprint", certificateThumbprint);
            CloudTracing.enter(invocationId, this, "getAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/networking/" + virtualNetworkName + "/gateway/clientrootcertificates/" + certificateThumbprint;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-11-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        ClientRootCertificateGetResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ClientRootCertificateGetResponse();
        result.setCertificate(StreamUtils.toString(responseContent));
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
    
    /**
    * The List Client Root Certificates operation returns a list of all the
    * client root certificates that are associated with the specified virtual
    * network in Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205130.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @return The response to the list client root certificates request.
    */
    @Override
    public Future<ClientRootCertificateListResponse> listAsync(final String virtualNetworkName)
    {
        return this.getClient().getExecutorService().submit(new Callable<ClientRootCertificateListResponse>() { 
            @Override
            public ClientRootCertificateListResponse call() throws Exception
            {
                return list(virtualNetworkName);
            }
         });
    }
    
    /**
    * The List Client Root Certificates operation returns a list of all the
    * client root certificates that are associated with the specified virtual
    * network in Windows Azure.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/dn205130.aspx for
    * more information)
    *
    * @param virtualNetworkName The name of the virtual network for this
    * gateway.
    * @return The response to the list client root certificates request.
    */
    @Override
    public ClientRootCertificateListResponse list(String virtualNetworkName) throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        if (virtualNetworkName == null)
        {
            throw new NullPointerException("virtualNetworkName");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace)
        {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("virtualNetworkName", virtualNetworkName);
            CloudTracing.enter(invocationId, this, "listAsync", tracingParameters);
        }
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/" + this.getClient().getCredentials().getSubscriptionId() + "/services/networking/" + virtualNetworkName + "/gateway/clientrootcertificates";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml");
        httpRequest.setHeader("x-ms-version", "2013-11-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        if (shouldTrace)
        {
            CloudTracing.sendRequest(invocationId, httpRequest);
        }
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        if (shouldTrace)
        {
            CloudTracing.receiveResponse(invocationId, httpResponse);
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            if (shouldTrace)
            {
                CloudTracing.error(invocationId, ex);
            }
            throw ex;
        }
        
        // Create Result
        ClientRootCertificateListResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ClientRootCertificateListResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("ClientRootCertificates");
        Element clientRootCertificatesSequenceElement = elements.getLength() > 0 ? ((Element) elements.item(0)) : null;
        if (clientRootCertificatesSequenceElement != null)
        {
            for (int i1 = 0; i1 < clientRootCertificatesSequenceElement.getElementsByTagName("ClientRootCertificate").getLength(); i1 = i1 + 1)
            {
                org.w3c.dom.Element clientRootCertificatesElement = ((org.w3c.dom.Element) clientRootCertificatesSequenceElement.getElementsByTagName("ClientRootCertificate").item(i1));
                ClientRootCertificateListResponse.ClientRootCertificate clientRootCertificateInstance = new ClientRootCertificateListResponse.ClientRootCertificate();
                result.getClientRootCertificates().add(clientRootCertificateInstance);
                
                NodeList elements2 = clientRootCertificatesElement.getElementsByTagName("ExpirationTime");
                Element expirationTimeElement = elements2.getLength() > 0 ? ((Element) elements2.item(0)) : null;
                if (expirationTimeElement != null)
                {
                    Calendar expirationTimeInstance;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(simpleDateFormat.parse(expirationTimeElement.getTextContent()));
                    expirationTimeInstance = calendar;
                    clientRootCertificateInstance.setExpirationTime(expirationTimeInstance);
                }
                
                NodeList elements3 = clientRootCertificatesElement.getElementsByTagName("Subject");
                Element subjectElement = elements3.getLength() > 0 ? ((Element) elements3.item(0)) : null;
                if (subjectElement != null)
                {
                    String subjectInstance;
                    subjectInstance = subjectElement.getTextContent();
                    clientRootCertificateInstance.setSubject(subjectInstance);
                }
                
                NodeList elements4 = clientRootCertificatesElement.getElementsByTagName("Thumbprint");
                Element thumbprintElement = elements4.getLength() > 0 ? ((Element) elements4.item(0)) : null;
                if (thumbprintElement != null)
                {
                    String thumbprintInstance;
                    thumbprintInstance = thumbprintElement.getTextContent();
                    clientRootCertificateInstance.setThumbprint(thumbprintInstance);
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        if (shouldTrace)
        {
            CloudTracing.exit(invocationId, result);
        }
        return result;
    }
}