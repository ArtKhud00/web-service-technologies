
package org.itmo.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the genStandAlone package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCars_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars");
    private final static QName _GetCarsByParams_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars-by-params");
    private final static QName _GetCarsByParamsResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars-by-paramsResponse");
    private final static QName _GetCarsResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-carsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: genStandAlone
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCars }
     * 
     */
    public GetCars createGetCars() {
        return new GetCars();
    }

    /**
     * Create an instance of {@link GetCarsByParams }
     * 
     */
    public GetCarsByParams createGetCarsByParams() {
        return new GetCarsByParams();
    }

    /**
     * Create an instance of {@link GetCarsByParamsResponse }
     * 
     */
    public GetCarsByParamsResponse createGetCarsByParamsResponse() {
        return new GetCarsByParamsResponse();
    }

    /**
     * Create an instance of {@link GetCarsResponse }
     * 
     */
    public GetCarsResponse createGetCarsResponse() {
        return new GetCarsResponse();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCars }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCars }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-carsMsg")
    public JAXBElement<GetCars> createGetCars(GetCars value) {
        return new JAXBElement<GetCars>(_GetCars_QNAME, GetCars.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsByParams }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCarsByParams }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-cars-by-paramsMsg")
    public JAXBElement<GetCarsByParams> createGetCarsByParams(GetCarsByParams value) {
        return new JAXBElement<GetCarsByParams>(_GetCarsByParams_QNAME, GetCarsByParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsByParamsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCarsByParamsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-cars-by-paramsResponseMsg")
    public JAXBElement<GetCarsByParamsResponse> createGetCarsByParamsResponse(GetCarsByParamsResponse value) {
        return new JAXBElement<GetCarsByParamsResponse>(_GetCarsByParamsResponse_QNAME, GetCarsByParamsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCarsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-carsResponseMsg")
    public JAXBElement<GetCarsResponse> createGetCarsResponse(GetCarsResponse value) {
        return new JAXBElement<GetCarsResponse>(_GetCarsResponse_QNAME, GetCarsResponse.class, null, value);
    }

}
