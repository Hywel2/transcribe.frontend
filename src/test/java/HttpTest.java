//public class HttpTest {
//
////    @DisplayName("Check sendPost sends the http")
////    @Test
////    void testCheckSendPostSendsTheHttp() throws IOException {
////        Http http = new Http();
////        HttpResponse httpResponse= createHttpResponse();
////
////        new MockUp<CloseableHttpClient>() {
////            @Mock
////            HttpResponse execute(HttpUriRequest var1) throws IOException, ClientProtocolException {
////                return httpResponse;
////            }
////        };
////
////        InputStream inputStream = new FileInputStream("/Users/hywelgriffiths/Documents/IntellijProjects/sipho/transcriptionSoftware/audio.transcribe.front/src/test/java/resources/base64.txt");
////        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
////        String json = bufferedReader.readLine();
////
////        assertNull(http.sendPost(json));
////
////    }
////
////    private HttpResponse createHttpResponse() {
////        HttpResponse httpResponse = new HttpResponse() {
////
////            @Override
////            public ProtocolVersion getProtocolVersion() {
////                return null;
////            }
////
////            @Override
////            public boolean containsHeader(String s) {
////                return false;
////            }
////
////            @Override
////            public Header[] getHeaders(String s) {
////                return new Header[0];
////            }
////
////            @Override
////            public Header getFirstHeader(String s) {
////                return null;
////            }
////
////            @Override
////            public Header getLastHeader(String s) {
////                return null;
////            }
////
////            @Override
////            public Header[] getAllHeaders() {
////                return new Header[0];
////            }
////
////            @Override
////            public void addHeader(Header header) {
////
////            }
////
////            @Override
////            public void addHeader(String s, String s1) {
////
////            }
////
////            @Override
////            public void setHeader(Header header) {
////
////            }
////
////            @Override
////            public void setHeader(String s, String s1) {
////
////            }
////
////            @Override
////            public void setHeaders(Header[] headers) {
////
////            }
////
////            @Override
////            public void removeHeader(Header header) {
////
////            }
////
////            @Override
////            public void removeHeaders(String s) {
////
////            }
////
////            @Override
////            public HeaderIterator headerIterator() {
////                return null;
////            }
////
////            @Override
////            public HeaderIterator headerIterator(String s) {
////                return null;
////            }
////
////            @Override
////            public HttpParams getParams() {
////                return null;
////            }
////
////            @Override
////            public void setParams(HttpParams httpParams) {
////
////            }
////
////            @Override
////            public StatusLine getStatusLine() {
////                return null;
////            }
////
////            @Override
////            public void setStatusLine(StatusLine statusLine) {
////
////            }
////
////            @Override
////            public void setStatusLine(ProtocolVersion protocolVersion, int i) {
////
////            }
////
////            @Override
////            public void setStatusLine(ProtocolVersion protocolVersion, int i, String s) {
////
////            }
////
////            @Override
////            public void setStatusCode(int i) throws IllegalStateException {
////
////            }
////
////            @Override
////            public void setReasonPhrase(String s) throws IllegalStateException {
////
////            }
////
////            @Override
////            public HttpEntity getEntity() {
////                return null;
////            }
////
////            @Override
////            public void setEntity(HttpEntity httpEntity) {
////
////            }
////
////            @Override
////            public Locale getLocale() {
////                return null;
////            }
////
////            @Override
////            public void setLocale(Locale locale) {
////
////            }
////        };
////        return httpResponse;
////    }
//}
