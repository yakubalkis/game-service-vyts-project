import { useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/purchase/purchase-details/";

export default function TotalAnalysisOfUser({onClickBackBtn}) {

    const [userId, setUserId] = useState("");
    const [analyzes, setAnalyzes] = useState([]);

    function handleChange(event) {
        const {name, value} = event.target;

        setUserId(value);
    }

    function handleSubmit(e) {
        e.preventDefault();

        const config = getConfig();

        axios.get(API_URL + userId, config)
            .then((res) => {
                console.log(res);
                setAnalyzes(res.data)
            }).catch(res => {
                console.log(res)
                window.alert(res.message)
            })
    }

    const analysisDisplay = analyzes.length > 0 ?  
        analyzes.map((analys,i) => {
            return (
                <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={i}>
                    <li>Username: {analys[0]}</li>
                    <li>Purchase Date: {analys[1]}</li>
                    <li>Amount: {analys[2]}</li>
                    <li>Item Name: {analys[3]}</li>
                </ul>
            )
        }) : null;

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>It shows how many units a user purchased of which product on which shopping date.</p>
            <form className="w-50">
                <div className="form-group">
                    <label>User ID: </label>
                    <input className="form-control form-control-lg bg-light fs-6" placeholder="User ID" type="text" name="userId" value={userId} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Click to process(Send HTTP Request)</button>
                </div>
            </form>
            <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap",marginTop:"2rem" }}>
                {analysisDisplay}
            </div>
        </div>
    )
}