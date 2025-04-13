package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.ClienteDto;
import EF.__FloresRoman.api_rest.Dto.ClienteRegistroDto;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import EF.__FloresRoman.api_rest.Model.Cliente;
import EF.__FloresRoman.api_rest.Repository.ClienteRepository;
import EF.__FloresRoman.api_rest.Repository.Projection.ClienteProjection;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    // Obtener todos los clientes (proyección)
    public List<ClienteProjection> getAllClientes() {
        return clienteRepository.findAllProjectedBy();
    }
    // Obtener un cliente por ID (proyección)
    public ClienteProjection getClienteById(Long id) {
        return clienteRepository.findProjectedById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }
    // Registrar un nuevo cliente
    public GenericResponseDto registrarCliente(ClienteRegistroDto clienteRegistroDto) {
        Cliente cliente = new Cliente();
        cliente.setNombreEmpresa(clienteRegistroDto.getNombreEmpresa());
        Cliente clienteGuardado = clienteRepository.save(cliente);
        ClienteDto dto = new ClienteDto(clienteGuardado.getIdCliente(), clienteGuardado.getNombreEmpresa());
        GenericResponseDto response = new GenericResponseDto();
        response.setMessage("Cliente registrado exitosamente");
        response.setSuccess(true);
        response.setData(dto);
        return response;
    }
    // Actualizar un cliente existente
    public GenericResponseDto actualizarCliente(Long id, ClienteRegistroDto clienteRegistroDto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        cliente.setNombreEmpresa(clienteRegistroDto.getNombreEmpresa());
        Cliente clienteActualizado = clienteRepository.save(cliente);
        ClienteDto dto = new ClienteDto(clienteActualizado.getIdCliente(), clienteActualizado.getNombreEmpresa());
        GenericResponseDto response = new GenericResponseDto();
        response.setMessage("Cliente actualizado exitosamente");
        response.setSuccess(true);
        response.setData(dto);
        return response;
    }
    // Eliminar un cliente
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}