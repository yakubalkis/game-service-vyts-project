import { useEffect, useState } from "react";
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/logs";

export default function AllLogs({onClickBackBtn}) {
    const [logs, setLogs] = useState([])
    const [username, setUsername] = useState("");
    const [isShow, setIsShow] = useState(false);
    const [usernameLabel, setUsernameLabel] = useState("")  


    function handleChange(event) {
        const {name, value} = event.target;
        setUsername(value);
    }

    function handleSubmit() {
        const config = getConfig();

        axios.get(API_URL+ "/" + username, config)
            .then((res) => {
                setLogs(res.data)
                setIsShow(true)
                setUsernameLabel(username)
            }).catch((err) => {
                window.alert(err.response.data+ " "+ err.message);
            })
    }

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                setLogs(res.data)
            }).catch((err) => {
               window.alert("Something went wrong." + " " + err.message);
            })
    }, [])

    const logList = logs.map((log) => {
        return(
            <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={log.id}>
                <li>ID: {log.id}</li>
                <li>Message: {log.message}</li>
                <li>Type: {log.type}</li>
            </ul>
        )
    })

    return(
        <div style={{maxWidth:"100%"}}>
        <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
       
        <div className="form-group w-50">
            <label>Enter username to get user's logs: </label>
            <input className="form-control form-control-lg bg-light fs-6" placeholder="Username" type="text" name="username" value={username} onChange={handleChange} />
        </div>
        <div className="form-group w-50">
            <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleSubmit}>Get User's Logs(HTTP Request)</button>
        </div>
       
        <p>{!isShow ? "All Logs" : usernameLabel + "'s logs"}</p>
        <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
            {logList}
        </div>
    </div>
    )

}