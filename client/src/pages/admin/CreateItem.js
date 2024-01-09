import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";
import MessageToast from "../../components/MessageToast";

const API_URL = "http://localhost:8080/items/add";

export default function CreateItem({onClickBackBtn}) {
    const [show, setShow] = useState(false); // is used to show MessageToast component, will be passed to MessageToast as prop
    const [message, setMessage] = useState(""); // is also used for message in MessageToast component

    const [inputAll, setInputAll] = useState({ // for inputs in form
        categoryId: "",
        itemName: "",
        symbol: "",
    });
    
    function handleChange(event){ // get values from inputs
        const {name, value} = event.target;

        setInputAll(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        });
    }

    function handleSubmit(e) {
        e.preventDefault();
        const config = getConfig();

        const itemRequest = {categoryId: inputAll.categoryId, itemName: inputAll.itemName, symbol: inputAll.symbol};


        axios.post(API_URL, itemRequest, config)
            .then((res) => {
                if(res.status === 200){
                    console.log(res);
                    setMessage(res.data.Message); // show message when process is successful
                    setShow(true);
                    setInputAll({ // clean up form inputs
                        categoryId: "",
                        itemName: "",
                        symbol: "",                  
                    });
                 }
            }).catch(res => {
                console.log(res)
                window.alert(res.response.data)
            })
    }


    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <form className="w-100">
                <div className="form-group w-50">
                    <label>Category ID:(Leave it blank if you want to create item without category relationship.) </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Category ID" type="text" name="categoryId" value={inputAll.categoryId} onChange={handleChange} />
                </div>
                <div className="form-group w-50">
                    <label>Item Name: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Item name" type="text" name="itemName" value={inputAll.itemName} onChange={handleChange} />
                </div>
                <div className="form-group w-50">
                    <label>Symbol(filename): </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Symbol" type="text" name="symbol" value={inputAll.symbol} onChange={handleChange} />
                </div>
                <div className="form-group w-50">
                    <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Create Item(Send HTTP Request)</button>
                </div>
            </form>
            <div style={{ position: 'fixed', left: 0, bottom: 0 }}>
                <MessageToast show={show} message={message} setShow={setShow} />
            </div>
        </div>
    )
}