import { useNavigate } from 'react-router-dom';
import s from './MenuCard.module.css'



const MenuCard = ({title, subTitle, content, route}) => {


    const nav = useNavigate();

    return <div className={s.menuCard} onClick={() => nav(route)}>
        <div className={s.menuCardTitle}>{title}</div>
        <div>{subTitle}</div><br /> <br/>
        <div>{content}</div>
        </div>

}

export default MenuCard;