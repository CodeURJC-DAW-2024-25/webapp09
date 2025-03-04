package es.grupo9.practica1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GraphicController {
    @GetMapping("/graphic")
    public void graphicGenerator(@RequestParam String param, HttpServletResponse response) throws IOException {
        // Crear dataset con datos de ejemplo
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Ventas", "Enero");
        dataset.addValue(20, "Ventas", "Febrero");
        dataset.addValue(30, "Ventas", "Marzo");

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Ventas Mensuales", "Mes", "Cantidad", dataset);

        // Configurar la respuesta HTTP para devolver una imagen
        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 600, 400);
    }
}