import { useNavigate } from 'react-router-dom';
import s from './MenuCard.module.css';



const MenuCard = ({title, subTitle, content, link, route, width, routeData}) => {


    const nav = useNavigate();

    return <div style={width? {width: width} : {width: '100%'}} className={s.menuCard} onClick={routeData ? () => nav(route,{state: routeData}) : ()=> nav(route)}>
        <div className={s.menuCardTitle}>{title}</div>
        {link ? <a href={link}>{link}</a>: <></>}
        <div>{subTitle}</div><br /> <br/>
        <div>{content}</div>
        </div>

}

export default MenuCard;