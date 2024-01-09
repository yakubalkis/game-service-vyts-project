import { useState, useEffect } from "react";
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/purchase/all";

export default function AllPurchases({onClickBackBtn}) {
    const [purchaseList, setPurchaseList] = useState([]);

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                setPurchaseList(res.data)
                console.log(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    }, []);

    const purchaseListDisplay = purchaseList.map((purchase) => {
        return (
                <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={purchase.id}>
                    <li>ID: {purchase.id}</li>
                    <li>Amount: {purchase.amount}</li>
                    <li>Price: {purchase.purchasePrice}</li>
                    <li>Price Type: {purchase.priceType}</li>
                    <li>Bought Item: {purchase.boughtItemName}</li>
                    <li>Bought Item ID: {purchase.boughtItemId}</li>
                    <li>Date: {purchase.purchaseDate}</li>
                    <li>Amount: {purchase.amount}</li>
                    <li>User ID: {purchase.userID}</li>
                </ul>
            )
    });

    return (
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>All Purchases</p>
            <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
                {purchaseListDisplay}
            </div>
        </div>
    )

}