import com.ms.Color
import com.ms.Palette
import com.ms.Polygon
import com.ms.Renderer
import com.ms.ShapeFactory
import com.ms.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

class Test extends Specification {
    void setup() {
        //setup code that runs before each test method
    }

    void setupSpec(){
        //setup code that run once at the start of specification
    }
    void cleanup() {
        //clean up method that run after each test method
    }

    void cleanupSpec() {
        //clean up method that tears down everything at the end of all tests
    }

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        given:
        def polygon = new com.ms.Polygon(4);

        when:
        int side = polygon.sides;

        then:
        side == 4
    }

    def "should throw Exceptions"() {
        when:
        def polygon = new Polygon(1);

        then:
        def exception = thrown(TooFewSidesException.class)
        exception.numberOfSides == 1

    }

    def "should expect an Exception to be thrown for a number of invalid input side: #sides"() {
        when:
        def polygon = new Polygon(sides);

        then:
        def exception = thrown(TooFewSidesException.class)
        exception.numberOfSides == sides

        where:
        sides << [-1, 1, 0, 2]
    }

    def "should be able to create a polygon with #sides sides"() {
        expect:
        new Polygon(sides).sides == sides

        where:
        sides << [3, 4, 6, 8]
    }

    def "should use data table to calculate max. Max of #a anf #b is #max" (){
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        99| 5 || 99
        45| 7 || 45
        0 | 0 || 0
    }

    def "should be able to use mock" (){
        given:
        Renderer renderer = Mock()
        @Subject
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a stub"(){
        given:
        Palette palette = Stub()
        palette.getPrimaryColour() >> Color.RED
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour()  == Color.RED
    }

    def "should demonstrate 'verifyAll'"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        verifyAll (polygon){
            sides == 4
            renderer == renderer
        }
    }
}