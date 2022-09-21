package com.example.Dva.Controller;
import com.example.Dva.Rep.*;
import com.example.Dva.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
@Autowired
private PostRepository postRepository;
@Autowired
    private Postrep postRep;
@Autowired
private DataRepository dataRepository;
@Autowired
private AwardsRepository awardsRepository;
@Autowired
private PostsRepository postsRepository;
@Autowired
private LicRepository licRepository;
@Autowired
private PromoRepository promoRepository;
@Autowired
private PurchaseRepository purchaseRepository;
@Autowired
private CustomerRepository customerRepository;
    @GetMapping("/reg")
    public String registration(){return "Reg";}
    @PostMapping("/reg")
    public String addUser(User user, Model model){
        User userFromDb=userRepository.findByUsername(user.getUsername());
        if (userFromDb!=null){
            model.addAttribute("message","Пользователь с таким именем уже существует");
        return "Reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/datatoawards")
    public String dataToAwards(Model model){
        Iterable<Data> datas = postRepository.findAll();
        model.addAttribute("datas", datas);
        Iterable<Awards> awards = awardsRepository.findAll();
        model.addAttribute("awards", awards);
        return "DataLincAwards";
    }
    @PostMapping("/datatoawards")
    public String dataToAwardsPm(@RequestParam String data, @RequestParam String awards, Model model) {

        Data data1 = dataRepository.findByName(data);
        Awards awards1 = awardsRepository.findByName(awards);
        data1.getAwardsList().add(awards1);
        awards1.getDataList().add(data1);
        postRepository.save(data1);
        //   awardsRepository.save(awards1);
        return "redirect:/show";
    }
    @GetMapping("/awardtolicence")
    public String addd(Model model){
        List<Awardlicens> pasport = licRepository.findAll();

        int siz=0;
         for (siz=pasport.size()-1;siz>=0;siz--){if (pasport.get(siz).getAwards()!=null){pasport.remove(siz);}}

        model.addAttribute("pasport", pasport);
        return "Linclicence";
    }
    @PostMapping("/awardtolicence")
    public String blogPostAdd(@RequestParam String name, @RequestParam String number, Model model)
    {

        Awardlicens pasport = licRepository.findByNumber(number);
        Awards person = new Awards(pasport, name);
        awardsRepository.save(person);
        return "redirect:/show";
    }
    @GetMapping("/")
    public String blogMain(Model model ){

        return "Main";
    }
    @GetMapping("/add")
    public String aad( Data data, Model model){
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts",posts);
        return "Adder";
    }
 /*@PostMapping("/add")
    public String Add(@RequestParam String name,
                      @RequestParam String fame,
                              @RequestParam String otch,
                              @RequestParam int place,
                              @RequestParam double zp,Model model
                      ){
         Data data =new Data(name,fame,otch,place,zp);

       postRepository.save(data);
        return "redirect:/show";
}*/
@PostMapping("/add")
    public String Add(@ModelAttribute ("data")  @Valid Data data, @RequestParam String postst, BindingResult bindingResult)
    {
if (bindingResult.hasErrors()){

    return "Adder";
}
data.setPosts(postsRepository.findByName(postst));
postRepository.save(data);
        return "redirect:/show";
    }
    @GetMapping("/add2")
    public String aad2(Data2 data2,Model model){
        return "Adder2";
    }
    @PostMapping("/add2")
    public String Add2(@ModelAttribute("data2") @Valid Data2 data2, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "Adder2";
        }

        postRep.save(data2);
        return "redirect:/show2";
    }
    @GetMapping("/show")
    public String show(Model model){
        Iterable<Data> posts=postRepository.findAll();
        model.addAttribute("posts",posts);
        return "Show";
    }
    @GetMapping("/show2")
    public String show2(Model model){
        Iterable<Data2> postRepAll=postRep.findAll();
        model.addAttribute("post",postRepAll);
        return "Show2";
    }
    @GetMapping ("/search")  public String blogFiltre(Model model){return "Search";}

    @PostMapping("/search")
    public String blogResult(@RequestParam String title, Model model)
    {
        List<Data> result = postRepository.findByName(title);
        model.addAttribute("result", result);
        return "Search";
    }
    @GetMapping ("/search2")  public String blogFiltre2(Model model){return "Search2";}

    @PostMapping("/search2")
    public String blogResult2(@RequestParam String title, Model model)
    {
        List<Data2> result = postRep.findByNameContaining(title);
        model.addAttribute("result", result);
        return "Search2";
    }
    @RequestMapping("/delete/{id}")
    public String delet1(@PathVariable long id,Model model){

        postRepository.deleteById(id);
        return "redirect:/show";
    }
    @RequestMapping("/delete2/{id}")
        public String delet2(@PathVariable long id,Model model){
        postRep.deleteById(id);
        return "redirect:/show2";
    }
    @GetMapping("/ismen/{id}")
    public String ismen(Model model,@ModelAttribute("data") Data data, @PathVariable long id ){
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts",posts);
        Data i=postRepository.findById(id);
        model.addAttribute("i", i);
        return "Ismen";
    }
    @PostMapping("/ismen/{idd}")
    public String ismeni(@ModelAttribute("data")
@PathVariable long idd ,
                       @Valid Data data,
                         @RequestParam String postst, BindingResult bindingResult, Model model){

            if (bindingResult.hasErrors()){
                Data i=postRepository.findById(idd);

               model.addAttribute("i", i);
                return "Ismen";
            }
        Data pcs = postRepository.findById(idd);
        pcs.setName(data.getName());
        pcs.setFame(data   .getFame());
        pcs.setOtch(data   .getOtch());
        pcs.setPlace(data   .getPlace());
        pcs.setZp(data   .getZp());
        pcs.setPosts(postsRepository.findByName(postst));
        postRepository.save(pcs);
            return "redirect:/show";

    }

    @GetMapping("/ismen2/{id}")
    public String ismen2(Model model,@ModelAttribute("data2") Data2 data2, @PathVariable long id  ) {

        Data2 i = postRep.findById(id);
        model.addAttribute("k", i);
        return "Ismen2";
    }
    @PostMapping("/ismen2/{idd}")
    public String ismeni2(@ModelAttribute("data2")
                         @Valid Data2 data2, BindingResult  bindingResult,@PathVariable long idd ,  Model model){

        if (bindingResult.hasErrors()){
            Data2 i=postRep.findById(idd);
            model.addAttribute("k", i);
            return "Ismen2";
        }
        Data2 pcs = postRep.findById(idd);
        pcs.setName(data2.getName());
        pcs.setAvtor(data2.getAvtor());
        pcs.setCount(data2.getCount());
        pcs.setYear(data2.getYear());
        pcs.setPrice(data2.getPrice());
        postRep.save(pcs);
        return "redirect:/show2";

    }
    @GetMapping("/customer/add")
    public String aadCus( Customer customer, Model model){
        return "AddCustomer";
    }

    @PostMapping("/customer/add")
    public String AddCus(@ModelAttribute ("customer")  @Valid Customer customer,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "AddCustomer";
        }
        customerRepository.save(customer);
        return "redirect:/customer/show";
    }
    @GetMapping("/purchase/add")
    public String aadPur( Purchase purchase, Model model){
        Iterable<Customer> customers = customerRepository.findAll();
        model.addAttribute("customer",customers);
        return "AddPurchase";
    }

    @PostMapping("/purchase/add")
    public String AddPur(@ModelAttribute ("purchase")  @Valid Purchase purchase, @RequestParam String postst, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "AddPurchase";
        }
        purchase.setCustomer(customerRepository.findByName(postst));
        purchaseRepository.save(purchase);
        return "redirect:/purchase/show";
    }
    @GetMapping("/promo/add")
    public String aadPromo( Customer customer, Model model){
        Iterable<Customer> customers = customerRepository.findAll();
        model.addAttribute("customer",customers);
        return "AddPromo";
    }

    @PostMapping("/promo/add")
    public String AddPromo(@ModelAttribute ("promo")  @Valid Promo promo, @RequestParam String postst, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "AddPromo";
        }
        promoRepository.save(promo);
        return "redirect:/promo/show";
    }
    @GetMapping("/promo/show")
    public String PromoShow(Model model){
        Iterable<Promo> posts=promoRepository.findAll();
        model.addAttribute("posts",posts);
        return "PromoShow";
    }
    @GetMapping("/purchase/show")
    public String purchaseShow(Model model){
        Iterable<Purchase> posts=purchaseRepository.findAll();
        model.addAttribute("posts",posts);
        return "PurchaseShow";
    }
    @GetMapping("/customer/show")
    public String CustomerShow(Model model){
        Iterable<Customer> posts=customerRepository.findAll();
        model.addAttribute("posts",posts);
        return "CustomerShow";
    }

}
