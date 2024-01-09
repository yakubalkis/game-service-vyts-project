import { useState } from "react"
import AllUsers from "./AllUsers";
import AllPurchases from "./AllPurchases";
import AllCategories from "./AllCategories";
import AllItems from "./AllItems";
import AllLogs from "./AllLogs";
import CreateCategory from "./CreateCategory";
import CreateItem from "./CreateItem";

export default function AdminHomePage() {

    const [renderOption, setRenderOption] = useState("");

    function handleClick(componentName) {
        setRenderOption(state => componentName);
    }

    function onClickBackBtn() {
        setRenderOption(state => "");
    }

    return(
        <div style={{marginTop: "20px", display: "flex", flexWrap:"wrap"}}>
            {renderOption==="allUsers" &&  <AllUsers onClickBackBtn={onClickBackBtn} />}
            {renderOption==="purchases" && <AllPurchases onClickBackBtn={onClickBackBtn} />}
            {renderOption==="categories" && <AllCategories onClickBackBtn={onClickBackBtn} />}
            {renderOption==="items" && <AllItems onClickBackBtn={onClickBackBtn} />}
            {renderOption==="logs" && <AllLogs onClickBackBtn={onClickBackBtn} />}
            {renderOption==="createCategory" && <CreateCategory onClickBackBtn={onClickBackBtn} />}
            {renderOption==="createItem" && <CreateItem onClickBackBtn={onClickBackBtn} />}


            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("allUsers")}>
                                        Click to see all users
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("purchases")}>
                                        Click to see all purchases
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("categories")}>
                                        Click to see all categories
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("items")}>
                                        Click to see all items
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("logs")}>
                                        Click to see all logs
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("createCategory")}>
                                        Click to create a Category
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("createItem")}>
                                        Click to create an Item
                </button>
            }
            
            
        </div>
    )
}