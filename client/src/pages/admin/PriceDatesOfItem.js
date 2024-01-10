import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/price_dates/item/";

export default function PriceDatesOfItem({onClickBackBtn}) {

    const [itemId, setItemId] = useState("");
    const [priceDates, setPriceDates] = useState([]);
    
    function handleChange(event) {
        const {name, value} = event.target;

        setItemId(value);
    }

    function handleSubmit(e) {
        e.preventDefault();

        const config = getConfig();

        axios.get(API_URL + itemId, config)
            .then((res) => {
                console.log(res);
                setPriceDates(res.data)
            }).catch(res => {
                console.log(res)
                window.alert(res.message)
            })
    }

    const priceDatesDisplay = priceDates.length > 0 ?  
        priceDates.map((priceDate) => {
            return (
                <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={priceDate.id}>
                    <li>ID: {priceDate.id}</li>
                    <li>Price: {priceDate.price}</li>
                    <li>Price Date: {priceDate.priceDate}</li>
                    <li>Price Type: {priceDate.priceType}</li>
                </ul>
            )
        }) : null;

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <form className="w-100">
                <div className="form-group">
                    <label>Item ID: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="Item ID" type="text" name="itemId" value={itemId} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Click to get price dates(Send HTTP Request)</button>
                </div>
            </form>
            <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap",marginTop:"2rem" }}>
                {priceDates.length > 0 ? priceDatesDisplay : <></>}
            </div>
        </div>
    )

}