import { useEffect, useState } from "react";
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/items";


export default function AllItems({onClickBackBtn}) {

    const [items, setItems] = useState([]);

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                setItems(res.data)
                console.log(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    },[])

    const itemList = items.map((item) => {
        return(
            <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={item.id}>
                <li>ID: {item.id}</li>
                <li>Item name: {item.itemName}</li>
                <li>Symbol filename: {item.symbol}</li>
                <li>Category name: {item.categoryName}</li>
                <li>Current Price: {item.currentPriceDate}</li>
            </ul>
        )
    })

    return(
    <div style={{maxWidth:"100%"}}>
        <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
        <p>All Items</p>
        <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
            {itemList}
        </div>
    </div>
    )
}