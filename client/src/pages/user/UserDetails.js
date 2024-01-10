import { useEffect, useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

export default function UserDetails({onClickBackBtn}) {
    
    const [user, setUser] = useState([]);
    const username =  JSON.parse(localStorage.getItem("user"));

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL+username, config)
            .then((res) => {
                setUser(res.data)
                console.log(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    },[])

    const userDisplay = (<ul style={{marginLeft:"1rem", paddingRight:"5px" ,border: "2px solid blue", borderRadius:"5px"}} key={user.id}>
                            <li>ID: {user.id}</li>
                            <li>Role: {user.role}</li>
                            <li>Email: {user.email}</li>
                            <li>Username: {user.username}</li>
                            <li>Phone number: {user.phoneNumber}</li>
                            <li>Date of join: {user.dateOfJoin}</li>
                            <li>Current Game Money: {user.currentGameMoney}</li>
                            <li>Level point: {user.level}</li>
                            <li>Level name: {user.levelName}</li>
                            <li>Rank point: {user.rank}</li>
                            <li>Rank name: {user.rankName}</li>
                        </ul>)

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>Your Details</p>
            {userDisplay}
        </div>
    )

}