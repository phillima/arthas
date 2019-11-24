---
title: 'Annotation Sniffer: A tool to Extract Code Annotations Metrics'
tags:
   - Java
   - metadata
   - annotations
   - software-engineering
   - source-code-analysis
authors:
  - name: Phyllipe Lima
    orcid: 0000-0002-8358-4405
    affiliation: "1,2" #
  - name: Eduardo Guerra
    orcid: 0000-0001-5555-3487
    affiliation: "2" #
  - name: Paulo Meirelles
    orcid: 0000-0002-8923-2814
    affiliation: "3"
affiliations:
 - name: CDG, National Institute of Telecommunications - INATEL, Brazil
   index: 1
 - name: LAC, National Institute for Space Research - INPE, Brazil
   index: 2
 - name: DIS, Federal University of São Paulo - UNIFESP, Brazil
   index: 3
date: 24 Novembrt 2019
bibliography: paper.bib
---

# Summary
Since the introduction of code annotations in the Java language, this feature has been widely adopted by software developers across the globe. Main enterprise frameworks make extensive use of code annotations as a replacement for XML based solutions. Code annotations are inserted directly on code elements, providing a simple way to introduce custom metadata. To aid in our research that aims understanding how developers use this feature, we developed the Annotation Sniffer. An open source tool that extracts a suite of metrics dedicated to code annotations.

Source code metrics can retrieve information from software to assess its characteristics. Well-known techniques use metrics associated with rules to detect bad smells on the source code [@Lanza2006,@VanRompaey2007]. However, traditional code metrics does not recognize code annotations on programming elements, which can lead to an incomplete code assessment [@Guerra2009]. For example, a domain class can be considered simple using current complexity metrics. However, it can contain complex annotations for object-XML mapping. Also, using a set of annotations couples the application to a framework that can interpret them and current coupling metrics does not explicitly handle this.

The Java language provides code annotation as a means to introduce custom metadata on programming elements. The annotation is declared directly on the source code, as opposed to an XML approach. The latter requires an external file with the metadata, and a path to this XML file must be provided to the software consuming the metadata.

To automate the process of extracting the code annotation metrics, we developed an open source tool called Annotation Sniffier (ASniffer). It obtains the metrics values and outputs them in an XML report. The goal of this paper is to present this novel tool, since no other similar was found.

# Metadata and Code Annotations

# Annotation Metrics

```java
import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.DiscriminatorColumn;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@AssociationOverrides(value = {
      @AssociationOverride(name="ex",
         joinColumns = @JoinColumn(name="EX_ID")),
      @AssociationOverride(name="other",
         joinColumns = @JoinColumn(name="O_ID"))})
@NamedQuery(name="findByName",
      query="SELECT c " +
            "FROM Country c " + 
            "WHERE c.name = :name")
@Stateless
public class Example {...

   @TransactionAttribute(SUPPORTS)
   @DiscriminatorColumn(name = "type", discriminatorType = STRING)
   public String exampleMethodA(){...}

   @TransactionAttribute(SUPPORTS)
   public String exampleMethodB(){...}

   @TransactionAttribute(SUPPORTS)
   public String exampleMethodC(){...}
}

```
- Annotations in Class (AC): This metric counts the number of annotations declared on all code elements in a class, including nested annotations. In our example code, the value of AC is equal to 11. 

- Unique Annotations in Class (UAC): While AC counts all annotations, even repeated ones, UAC counts only distinct annotations. Two annotations are equal if they have the same name, and all arguments match. For instance, the annotation \texttt{@AssociationOverride} on line 10 is different from the one on line 12, for they have a nested annotation \texttt{@JoinColumn} that have different arguments. The first is \texttt{EX\_ID} while the latter is \texttt{O\_ID}. Hence they are distinct annotations and will be computed separately. The UAC value for the example class is nine. Notice that the annotations on lines 21, 25, and 28 are calculated only once for they are equal.

- Annotations Schemas in Class (ASC): An annotation schema represents a set of related annotations provided by a framework or tool. This measures how coupled a class is to a framework since different schemas on a class imply the class is using different frameworks. This value is obtained by tracking the imports used for the annotations. On the example code, the ASC value is two. The import \texttt{javax.persistence} is a schema provided by the JPA, and the import \texttt{javax.ejb} is provided by EJB.

- Arguments in Annotations (AA): Annotations may contain arguments. They can be a string, integer, or even another annotation. The AA metric counts the number of arguments contained in the annotation. For each annotation in the class, an AA value will be generated. For example, on line nine the \texttt{@AssociationOverrides} has only one argument ``\texttt{value}'', so the AA value is equal one. But \texttt{@AssociationOverride}, on line 10, contains two arguments, \texttt{name} and \texttt{joinColumns}, so the AA value is two. 

- Annotations in Element Declaration (AED): The AED metric counts how many annotations are declared in each code element, including nested annotations. In the example code, line 23, the method \texttt{exampleMethodA} has an AED value of two, it has the \texttt{@TransactionAttribute} and \texttt{@DiscriminatorColumn}

- Annotation Nesting Level (ANL): Annotations can have other annotations as arguments, which translates into nested annotations. ANL measures how deep an annotation is nested. The root level is considered value zero. The annotations \texttt{@Stateless} on line 18 has ANL value of zero, while \\
\texttt{@JoinColumn} on line 11 has ANL equals two. This data is because it has \texttt{@AssociationOverride}, line 10, as a first level, and then the \texttt{@AssociationOverrides}, line nine, adds another nesting level, hence the value ANL is two.

- LOC in Annotation Declaration (LOCAD): LOC (Line of Code), is a well-known metric that counts the number of code lines. The LOCAD is proposed as a variant of LOC that counts the number of lines used in an annotation declaration. \texttt{@AssociationOverrides} on line nine has a LOCAD value of five, while \texttt{@NamedQuery}, line 14, has LOCAD equals four.

- Number of Elements (NEC): This metric measures the number of elements that can be annotated in a class, i.e., the number of programming elements that can potentially be configured with code annotations. In the example class we have three methods, \texttt{exampleMethodA}, \texttt{exampleMethodB} and \texttt{exampleMethodC}, and the class declaration \texttt{Example}. Hence we have an NEC value of four.


# Annotation Sniffer 

# License 
Annotation Sniffer is licensed under the GNU Lesser General Public License v3.0

# Acknowledgements

This work is supported by FAPESP (Fundação de Amparo à Pequisa do Estado de São Paulo), grant 2014/16236-6 and CAPES (Coordenação de Aperfeiçoamento de Pessoal de Nível Superior )

# References