public class FieldView
{
    /**
     * Имя
     */
    protected String name;
    /**
     * Дата последней активности для отображения
     */
    protected String lastactiveForView;
    /**
     * Email
     */
    protected String emailPlayer;
    /**
     * Код страны
     */
    protected String countryCode;
    /**
     * Баланс
     */
    protected String fun;
    /**
     * Поле для сортировки по дате активности
     */
    protected Long lastactive;

    /**
     * Телефон
     */
    protected String phone;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastactiveForView()
    {
        return lastactiveForView;
    }

    public void setLastactiveForView(String lastactiveForView)
    {
        this.lastactiveForView = lastactiveForView;
    }

    public String getEmailPlayer()
    {
        return emailPlayer;
    }

    public void setEmailPlayer(String emailPlayer)
    {
        this.emailPlayer = emailPlayer;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getFun()
    {
        return fun;
    }

    public void setFun(String fun)
    {
        this.fun = fun;
    }

    public Long getLastactive()
    {
        return lastactive;
    }

    public void setLastactive(Long lastactive)
    {
        this.lastactive = lastactive;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
