import groovy.json.JsonSlurper
import spock.lang.Specification
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity

class APITester extends Specification {

    def "Test GET request to API endpoint"() {
        given:
        def httpClient = HttpClients.createDefault()
        def httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/1")

        when:
        def response = httpClient.execute(httpGet)

        then:
        response.getStatusLine().getStatusCode() == 200

        and:
        def responseBody = EntityUtils.toString(response.getEntity())
        responseBody != null

        and:
        // Additional assertions for response body
        def json = new JsonSlurper().parseText(responseBody)
        json.id == 1
        json.userId == 1
        json.title != null
        json.body != null
    }

    def "Test POST request to API endpoint"() {
        given:
        def httpClient = HttpClients.createDefault()
        def httpPost = new HttpPost("https://jsonplaceholder.typicode.com/posts")
        def requestBody = '{"title": "foo", "body": "bar", "userId": 1}'

        httpPost.setEntity(new StringEntity(requestBody))
        httpPost.setHeader("Content-type", "application/json")

        when:
        def response = httpClient.execute(httpPost)

        then:
        response.getStatusLine().getStatusCode() == 201

        and:
        def responseBody = EntityUtils.toString(response.getEntity())
        responseBody != null

        and:
        // Additional assertions for response body
        def json = new JsonSlurper().parseText(responseBody)
        json.title == 'foo'
        json.body == 'bar'
        json.userId == 1
    }
}