import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";
import MessageToast from "../../components/MessageToast";

const API_URL = "http://localhost:8080/price_dates/add";

export default function CreatePriceDate({onClickBackBtn}) {

    const [show, setShow] = useState(false); // is used to show MessageToast component, will be passed to MessageToast as prop
    const [message, setMessage] = useState(""); // is also used for message in MessageToast component

    const [inputAll, setInputAll] = useState({ // for inputs in form
        itemId: "",
        price: "",
        priceDate: "",
        priceType: ""
    });

    function handleChange(event) {
        const {name, value} = event.target;

        setInputAll(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        });
    }

    function handleSubmit(event) {
        event.preventDefault();

        const config = getConfig();

        const priceDateRequest = {itemId: inputAll.itemId, price: inputAll.price, priceDate: inputAll.priceDate, priceType: inputAll.priceType};

        axios.post(API_URL, priceDateRequest, config)
            .then((res) => {
                console.log(res);
                setMessage(res.data); // show message when process is successful
                setShow(true);
                setInputAll({ // clean up form inputs
                itemId: "",
                price:"",
                priceDate: "",
                priceType: ""                   
                });

            }).catch(res => {
                console.log(res)
                window.alert(res.message)
            })
    }

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <form className="w-100">
                <div className="form-group">
                    <label>Item ID: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Item ID" type="text" name="itemId" value={inputAll.itemId} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Price: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Price" type="text" name="price" value={inputAll.price} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Price Type: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Price Type" type="text" name="priceType" value={inputAll.priceType} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Price Date:(exmp: 2024/01/09) </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Price Date" type="text" name="priceDate" value={inputAll.priceDate} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Click to process(Send HTTP Request)</button>
                </div>
            </form>
            <div style={{ position: 'fixed', left: 0, bottom: 0 }}>
                <MessageToast show={show} message={message} setShow={setShow} />
            </div>
        </div>
    )
}