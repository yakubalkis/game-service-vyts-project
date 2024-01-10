import { useEffect, useState } from "react";
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/item/";

export default function UserInventory({onClickBackBtn}) {

    const [inventory, setInventory] = useState(null);


    useEffect(() => {
        const config = getConfig();
        const username = JSON.parse(localStorage.getItem("user"));

        axios.get(API_URL + username, config)
            .then((res) => {
                setInventory(res.data)
                console.log(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    }, [])

    const invetoryItems = inventory !== null ? inventory.items.map((item,i) => {
        return(
            <ul key={i} style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}}>
                <li>Item ID: {item.id}</li>
                <li>Item Name: {item.itemName}</li>
                <li>Symbol(filename): {item.symbol}</li>
            </ul>
        )
    }) : null

    const inventoryDisplay = (
        <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
            {invetoryItems}
        </div>
    )

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>Your Inventory</p>
            <p>Inventory ID: {inventory && inventory.id}</p>
            {inventoryDisplay}
        </div>
    )

}