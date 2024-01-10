import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";
import MessageToast from "../../components/MessageToast";

const API_URL = "http://localhost:8080/purchase";

export default function BuyAnItem({onClickBackBtn}) {

    const [show, setShow] = useState(false); // is used to show MessageToast component, will be passed to MessageToast as prop
    const [message, setMessage] = useState(""); // is also used for message in MessageToast component
    const [inputAll, setInputAll] = useState({ // for input values in form
        itemId: "",
        amount: "",
    }); 

    function handleChange(event){ // is used to get values from inputs
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
        const username = JSON.parse(localStorage.getItem("user"));

        const purchaseRequest = {username: username, itemId: inputAll.itemId, amount: inputAll.amount};

        axios.post(API_URL, purchaseRequest, config)
        .then((res) => {
            console.log(res);
            setMessage(res.data); // show message when process is successful
            setShow(true);
            setInputAll({ // clean up form inputs
                itemId:"",
                amount: "",           
                });
        }).catch(res => {
            console.log(res)
            window.alert(res.response.data)
        })

    }


    return(
        <div style={{maxWidth:"100%"}}>
            <p>Buy an item</p>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            
            
            <form className="w-100">
                <div className="form-group">
                    <label>Item ID: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Item ID" type="text" name="itemId" value={inputAll.itemId} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Amount: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Amount" type="text" name="amount" value={inputAll.amount} onChange={handleChange} />
                </div>

                <div className="form-group">
                    <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Click to buy(Send HTTP Request)</button>
                </div>
            </form>
            <div style={{ position: 'fixed', left: 0, bottom: 0 }}>
                <MessageToast show={show} message={message} setShow={setShow} />
            </div>
        </div>
    )
}