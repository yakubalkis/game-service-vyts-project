import { useEffect, useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/items/inventory-user-counts";

export default function TotalItemAnalysis({onClickBackBtn}) {

    const [analyzes, setAnalyzes] = useState([]);

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                console.log(res);
                setAnalyzes(res.data)
            }).catch(res => {
                console.log(res)
                window.alert(res.message)
            })
    }, []);

    const analysisDisplay = analyzes.length > 0 ?  
        analyzes.map((analys,i) => {
            return (
                <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={i}>
                    <li>Item name: {analys[0]}</li>
                    <li>Number of unique user which bought this item: {analys[1]}</li>
                    <li>Number of inventory which item stayed at: {analys[2]}</li>
                    <li>Total number of purchases: {analys[3]}</li>
                </ul>
            )
        }) : null;

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>It first shows the item name of an item, the number of users who own this item, the number of inventory this item is included in, and the total number of sales of this item.</p>
            <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap",marginTop:"2rem" }}>
                {analysisDisplay}
            </div>
        </div>
    )

}

