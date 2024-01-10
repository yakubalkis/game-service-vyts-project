import { useState } from "react"
import AllUsers from "./AllUsers";
import AllPurchases from "./AllPurchases";
import AllCategories from "./AllCategories";
import AllItems from "./AllItems";
import AllLogs from "./AllLogs";
import CreateCategory from "./CreateCategory";
import CreateItem from "./CreateItem";
import PriceDatesOfItem from "./PriceDatesOfItem";
import CreatePriceDate from "./CreatePriceDate";
import TotalPurchaseAnalysisOfUser from "./TotalPurchaseAnalysisOfUser";
import TotalItemAnalysis from "./TotalItemAnalysis";

export default function AdminHomePage() {

    const [renderOption, setRenderOption] = useState("");

    function handleClick(componentName) {
        setRenderOption(state => componentName);
    }

    function onClickBackBtn() {
        setRenderOption(state => "");
    }

    return(
        <>
        <h2>Admin Panel</h2>
        <div style={{marginTop: "20px", display: "flex", flexWrap:"wrap"}}>
            {renderOption==="allUsers" &&  <AllUsers onClickBackBtn={onClickBackBtn} />}
            {renderOption==="purchases" && <AllPurchases onClickBackBtn={onClickBackBtn} forWho={"all"} />}
            {renderOption==="categories" && <AllCategories onClickBackBtn={onClickBackBtn} />}
            {renderOption==="items" && <AllItems onClickBackBtn={onClickBackBtn} />}
            {renderOption==="logs" && <AllLogs onClickBackBtn={onClickBackBtn} />}
            {renderOption==="createCategory" && <CreateCategory onClickBackBtn={onClickBackBtn} />}
            {renderOption==="createItem" && <CreateItem onClickBackBtn={onClickBackBtn} />}
            {renderOption==="priceDatesOfItem" && <PriceDatesOfItem onClickBackBtn={onClickBackBtn} />}
            {renderOption==="createPriceDate" && <CreatePriceDate onClickBackBtn={onClickBackBtn} />}
            {renderOption==="totalPurchaseAnalysisOfUser" && <TotalPurchaseAnalysisOfUser onClickBackBtn={onClickBackBtn} />}
            {renderOption==="totalItemAnalysisOfUser" && <TotalItemAnalysis onClickBackBtn={onClickBackBtn} />}


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
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("priceDatesOfItem")}>
                                        Click to get price dates of an Item
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("createPriceDate")}>
                                        Click to create price date for an Item
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("totalPurchaseAnalysisOfUser")}>
                                        Click to get purchase analysis of an user
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("totalItemAnalysisOfUser")}>
                                        Click to get item analysis
                </button>
            }
            
            
        </div>
        </>
    )
}