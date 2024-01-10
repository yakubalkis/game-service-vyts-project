import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";
import MessageToast from "../../components/MessageToast";

const API_URL = "http://localhost:8080/budget/";

export default function AddGameMoney({onClickBackBtn}) {
    const [show, setShow] = useState(false); // is used to show MessageToast component, will be passed to MessageToast as prop
    const [message, setMessage] = useState(""); // is also used for message in MessageToast component

    const [amount, setAmount] = useState();
    const [processType, setProcessType] = useState()

    function handleChange(event) {
        const {name, value} = event.target;

        setAmount(value);
    }

    function handleOption(event){ // is used for radio buttons 
        setProcessType(event.target.value);
    }

    function handleSubmit(e) {
        e.preventDefault();
        const username = JSON.parse(localStorage.getItem("user"));
        const config = getConfig();

        const budgetRequest = {processType: processType, amount: amount }

        axios.put(API_URL + username, budgetRequest, config)
            .then((res) => {
                console.log(res);
                setMessage(res.data); // show message when process is successful
                setShow(true);
                setAmount("");
                setProcessType("")
            }).catch(res => {
                console.log(res)
                window.alert(res.message)
            })
    }


    return(
        <div style={{maxWidth:"100%"}}>
            <p>Add game money to your budget..</p>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            
            
            <form className="w-100">
                <div className="form-group">
                    <label>Amount: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Amount" type="text" name="amount" value={amount} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Choose process:</label><br />
                    <input className="form-check-input" type="radio" name="processType" value="increase" onChange={handleOption} checked={processType === "increase"} />
                    <label style={{ marginLeft: ".2rem" }}>Increase</label>
                    <input className="form-check-input" style={{ marginLeft: "1rem" }} type="radio" name="processType" value="decrease" onChange={handleOption} checked={processType === "decrease"} />
                    <label style={{ marginLeft: ".2rem" }}>Decrease</label>
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