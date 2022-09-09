package com.example.Dva.Controller;
import com.example.Dva.Rep.PostRepository;
import com.example.Dva.Rep.Postrep;
import com.example.Dva.models.Data;
import com.example.Dva.models.Data2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Controller
public class MainController {
@Autowired
private PostRepository postRepository;
@Autowired
    private Postrep postRep;
    @GetMapping("/")
    public String blogMain(Model model ){

        return "Main";
    }
    @GetMapping("/add")
    public String aad( Data data, Model model){
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
    public String Add(@ModelAttribute ("data") @Valid Data data, BindingResult bindingResult)
    {
if (bindingResult.hasErrors()){

    return "Adder";
}
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

        Data i=postRepository.findById(id);
        model.addAttribute("i", i);
        return "Ismen";
    }
    @PostMapping("/ismen/{idd}")
    public String ismeni(@ModelAttribute("data")
@PathVariable long idd ,
                       @Valid Data data, BindingResult bindingResult, Model model){

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

}
