
package org.itmo.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the genStandalone package. 
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

    private final static QName _AuthorizationException_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "AuthorizationException");
    private final static QName _ForbiddenException_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "ForbiddenException");
    private final static QName _ServiceException_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "ServiceException");
    private final static QName _ThrottlingException_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "ThrottlingException");
    private final static QName _CreateNewCar_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "create-new-car");
    private final static QName _CreateNewCarResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "create-new-carResponse");
    private final static QName _DeleteCar_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "delete-car");
    private final static QName _DeleteCarResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "delete-carResponse");
    private final static QName _GetCars_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars");
    private final static QName _GetCarsByParams_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars-by-params");
    private final static QName _GetCarsByParamsResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-cars-by-paramsResponse");
    private final static QName _GetCarsResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "get-carsResponse");
    private final static QName _UpdateCar_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "update-car");
    private final static QName _UpdateCarResponse_QNAME = new QName("http://service.jaxws.ws.itmo.org/", "update-carResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: genStandalone
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CarServiceFault }
     * 
     */
    public CarServiceFault createCarServiceFault() {
        return new CarServiceFault();
    }

    /**
     * Create an instance of {@link CreateNewCar }
     * 
     */
    public CreateNewCar createCreateNewCar() {
        return new CreateNewCar();
    }

    /**
     * Create an instance of {@link CreateNewCarResponse }
     * 
     */
    public CreateNewCarResponse createCreateNewCarResponse() {
        return new CreateNewCarResponse();
    }

    /**
     * Create an instance of {@link DeleteCar }
     * 
     */
    public DeleteCar createDeleteCar() {
        return new DeleteCar();
    }

    /**
     * Create an instance of {@link DeleteCarResponse }
     * 
     */
    public DeleteCarResponse createDeleteCarResponse() {
        return new DeleteCarResponse();
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
     * Create an instance of {@link UpdateCar }
     * 
     */
    public UpdateCar createUpdateCar() {
        return new UpdateCar();
    }

    /**
     * Create an instance of {@link UpdateCarResponse }
     * 
     */
    public UpdateCarResponse createUpdateCarResponse() {
        return new UpdateCarResponse();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "AuthorizationException")
    public JAXBElement<CarServiceFault> createAuthorizationException(CarServiceFault value) {
        return new JAXBElement<CarServiceFault>(_AuthorizationException_QNAME, CarServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "ForbiddenException")
    public JAXBElement<CarServiceFault> createForbiddenException(CarServiceFault value) {
        return new JAXBElement<CarServiceFault>(_ForbiddenException_QNAME, CarServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "ServiceException")
    public JAXBElement<CarServiceFault> createServiceException(CarServiceFault value) {
        return new JAXBElement<CarServiceFault>(_ServiceException_QNAME, CarServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CarServiceFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "ThrottlingException")
    public JAXBElement<CarServiceFault> createThrottlingException(CarServiceFault value) {
        return new JAXBElement<CarServiceFault>(_ThrottlingException_QNAME, CarServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateNewCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "create-new-car")
    public JAXBElement<CreateNewCar> createCreateNewCar(CreateNewCar value) {
        return new JAXBElement<CreateNewCar>(_CreateNewCar_QNAME, CreateNewCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateNewCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "create-new-carResponse")
    public JAXBElement<CreateNewCarResponse> createCreateNewCarResponse(CreateNewCarResponse value) {
        return new JAXBElement<CreateNewCarResponse>(_CreateNewCarResponse_QNAME, CreateNewCarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "delete-car")
    public JAXBElement<DeleteCar> createDeleteCar(DeleteCar value) {
        return new JAXBElement<DeleteCar>(_DeleteCar_QNAME, DeleteCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "delete-carResponse")
    public JAXBElement<DeleteCarResponse> createDeleteCarResponse(DeleteCarResponse value) {
        return new JAXBElement<DeleteCarResponse>(_DeleteCarResponse_QNAME, DeleteCarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCars }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCars }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-cars")
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
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-cars-by-params")
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
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-cars-by-paramsResponse")
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
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "get-carsResponse")
    public JAXBElement<GetCarsResponse> createGetCarsResponse(GetCarsResponse value) {
        return new JAXBElement<GetCarsResponse>(_GetCarsResponse_QNAME, GetCarsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "update-car")
    public JAXBElement<UpdateCar> createUpdateCar(UpdateCar value) {
        return new JAXBElement<UpdateCar>(_UpdateCar_QNAME, UpdateCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jaxws.ws.itmo.org/", name = "update-carResponse")
    public JAXBElement<UpdateCarResponse> createUpdateCarResponse(UpdateCarResponse value) {
        return new JAXBElement<UpdateCarResponse>(_UpdateCarResponse_QNAME, UpdateCarResponse.class, null, value);
    }

}
