package br.com.docket.resources;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.docket.model.docket;
import br.com.docket.repository.docketRepository;

import org.springframework.stereotype.Controller;





@RestController 
@Controller
public class docketResources {
	
	
	//Redirecionamento de paginas
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String abrirIndex() {
		return "index";
		
	}
	
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public ModelAndView cadastar() {
	ModelAndView MV = new ModelAndView("cadastrar");
	return MV;
		
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.GET)
	public ModelAndView excluir() {
	ModelAndView MV = new ModelAndView("excluir");
	return MV;
		 
		
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.GET)
	public ModelAndView alterar() {
	ModelAndView MV = new ModelAndView("alterar");
	return MV;
		 
		
	}
	
	@RequestMapping(value="/visualizar", method=RequestMethod.GET)
	public ModelAndView visualizar() {
	ModelAndView MV = new ModelAndView("visualizar");
	return MV;
		 
		
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listar() {
	ModelAndView MV = new ModelAndView("Listartudo");
	return MV;
		 
		
	}
		 
	@Autowired
	docketRepository docketRepository;
	
	// Metodo Select ALL
	
	@RequestMapping(value = "/cartorios", method = RequestMethod.GET)

	public ResponseEntity<List<docket>> obterCartorios() {	
		List<docket> dockets; 		
		dockets = docketRepository.findAll();
		if (dockets.isEmpty())
			return new ResponseEntity<List<docket>>(dockets, HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<docket>>(dockets, HttpStatus.OK);
	}
	

	//Metodo Selecionar
	
		@RequestMapping(value= {"/cartorio/select/{ncartorio}"}, method = RequestMethod.GET)
		public ResponseEntity<?> obterCartorios(@PathVariable("ncartorio") Integer nCart )
		{
			Optional<docket> docket = docketRepository.findById(nCart);
			if (docket == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(docket);					
			return ResponseEntity.status(HttpStatus.OK).body(docket);
		}
		
		
   //Metodo Create
		
		@RequestMapping(value = "/cartorio/post", method = RequestMethod.POST)
		public ResponseEntity<docket> inserirCartorio(@RequestBody docket cartorioPost) {
			
			Optional<docket> docket; 
			
			docket = docketRepository.findById(cartorioPost.getId());
			
			if (docket.isPresent())
				return new ResponseEntity<docket>(docket.get(), HttpStatus.CONFLICT);
							
			docketRepository.save(cartorioPost);
			
			return new ResponseEntity<docket>(cartorioPost, HttpStatus.CREATED);
		}
				
		//Metodo deletar
		
		@RequestMapping(value= {"/cartorio/deletar/{ncartorio}"}, method = RequestMethod.DELETE)
		public ResponseEntity<?> eliminarCartorio(@PathVariable("ncartorio") Integer nCart )
		{
			if (!docketRepository.existsById(nCart) )
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		   docketRepository.deleteById(nCart);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}	

	
		//Metodo Alterar
		
				@RequestMapping(value= {"/cartorio/put"}, method = RequestMethod.PUT)
				public ResponseEntity<?> alterarCartorio(@RequestBody docket cartorio)
				{
					docket CartorioAtual = docketRepository.getOne(cartorio.getId());
					if (CartorioAtual == null)
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CartorioAtual);
					
					CartorioAtual.setId(cartorio.getId());
					CartorioAtual.setNome(cartorio.getNome());

					docketRepository.save(CartorioAtual);
					return ResponseEntity.status(HttpStatus.OK).body(CartorioAtual);
					
				}

				
			
		
	
	
}
