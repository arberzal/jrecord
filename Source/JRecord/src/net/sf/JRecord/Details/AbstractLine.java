/*
 * @Author Bruce Martin
 * Created on 18/04/2007
 *
 * Purpose:
 */
package net.sf.JRecord.Details;

import net.sf.JRecord.Common.AbstractFieldValue;
import net.sf.JRecord.Common.AbstractIndexedLine;
import net.sf.JRecord.Common.IFieldDetail;

/**
 * Interface to represent one Line in a file. Used through out JRecord / RecordEditor
 *
 * <p>The one important method is getFieldValue
 *
 * <p>Creating:
 * <pre>
 *              AbstractLine outLine = <font color="brown"><b>new</b></font> Line(oLayout);
 *     or
 *              AbstractLine outLine = <font color="brown"><b>new</b></font> XmlLine(oLayout, recordIdx);
 * </pre>
 *
 * <p>Getting a field value:
 * <pre>
 * 	            <font color="brown"><b>long</b></font> sku = saleRecord.getFieldValue("<font color="blue"><b>KEYCODE-NO</b></font>").asLong();
 * </pre>
 *
 * <p>Updating a field:
 * <pre>
 * 	            saleRecord.getFieldValue("<font color="blue"><b>KEYCODE-NO</b></font>").set(1331);
 * </pre>
 *
 * @author Bruce Martin
 *
 */
public interface AbstractLine extends AbstractIndexedLine {
    /**
     *   This method completely replaces a lines value. It is used to determine
     * a records prefered record layout
     *
     * @param rec buffer holding the record
     * @param start Start of the record
     * @param len length of the record
     */
    public abstract void replace(final byte[] rec, final int start,
            final int len);

    /**
     * Get the field values as raw Text
     *
     * @param recordIdx Index of the current layout used to retrieve the field
     * @param fieldIdx Index of the current field
     *
     * @return field value (raw Text)
     */
    public abstract String getFieldText(final int recordIdx, final int fieldIdx);

    /**
     * Get the full line as text
     *
     * @return line as text
     */
    public abstract String getFullLine();

    /**
     * Get the field value as Hex
     *
     * @param recordIdx Index of the current layout used to retrieve the field
     * @param fieldIdx Index of the current field
     *
     * @return field value as a Hex String
     */
    public abstract String getFieldHex(final int recordIdx, final int fieldIdx);

    /**
     * Get the field as bytes
     * @param recordIdx record index (or identifier)
     * @param fieldIdx field index (or identifier)
     * @return field as bytes
     */
    public abstract byte[] getFieldBytes(final int recordIdx, final int fieldIdx);


    /**
     * Get the Preferred Record Layout Index for this record (alternate method)
     *
     * @return Index of the Record Layout based on the Values
     */
    public abstract int getPreferredLayoutIdxAlt();

    /**
     * Get the byte value for a specified position and length
     * @param start starting position
     * @param len length to extract
     * @return the requested bytes
     */
    public abstract byte[] getData(int start, int len);

    /**
     * get The dat in the line as an Array of Bytes
     * @return Returns the record.
     */
    public abstract byte[] getData();

    /**
     * Set the line value to the supplied string
     * @param newVal new value for the line
     */
    public abstract void setData(String newVal);

    /**
     * Set the record Layout - Description of the Line
     * @param pLayout The layouts to set.
     */
    public abstract void setLayout(final LayoutDetail pLayout);

    /**
     * Get the Layout
     * @return Returns the layouts.
     */
    public abstract LayoutDetail getLayout();

    /**
     * Set Record Index to be used when writing this line
     * @param pWriteLayout The writeLayout to set.
     */
    public abstract void setWriteLayout(final int pWriteLayout);

    /**
     * Set the line provider
     *
     * @param pLineProvider The lineProvider to set.
     * 
     * @deprecated for use in JRecord
     */
    public abstract void setLineProvider(LineProvider pLineProvider);

    /**
     * Gets a fields value
     *
     * @param recordIdx Index of the RecordDescription to be used.
     * @param fieldIdx Index of the required field
     *
     * @return the request field (formated)
     *
     * @deprecated for use in JRecord, otherwise use {@link AbstractLine#getFieldValue(int, int)}
     */
    public abstract Object getField(final int recordIdx, final int fieldIdx);


    /**
     * Get a fields value
     *
     * @param fieldName field to retrieve
     *
     * @return fields Value
     *
     * @deprecated use {@link AbstractLine#getFieldValue(String)}
     */
    public abstract Object getField(String fieldName);

    /**
     * Gets a fields value
     *
     * @param recordIdx Index of the RecordDescription to be used.
     * @param fieldIdx Index of the required field
     *
     * @return the request field (formated)
     */
    public abstract AbstractFieldValue getFieldValue(final int recordIdx, final int fieldIdx);

    /**
     * Get a fields value
     *
     * @param field field to retrieve
     *
     * @return fields Value
     */
    public abstract AbstractFieldValue getFieldValue(IFieldDetail field);

    /**
     * Get a fields value
     *
     * @param fieldName field to retrieve
     *
     * @return fields Value
     */
    public abstract AbstractFieldValue getFieldValue(String fieldName);

    
    /**
     * Get a fields value
     *
     * @param fieldName field to retrieve
     *
     * @return fields Value
     */
   public abstract AbstractFieldValue getFieldValueIfExists(String fieldName);

    /**
     * Set a field via its name
     *
     * @param fieldName fieldname to be updated
     * @param value value to be applied to the field
     *
     * @deprecated use {@link AbstractLine#getFieldValue(IFieldDetail)}.set(..)
     */
    public abstract void setField(String fieldName, Object value);

    /**
     * Sets a field to a new value
     *
     * @param recordIdx record layout
     * @param fieldIdx field number in the record
     * @param val new value
     *
     * @deprecated for use in JRecord, use {@link AbstractLine#getFieldValue(int, int)}.set(..)
     */
    public abstract void setField(final int recordIdx, final int fieldIdx,
            Object val);

    /**
     * Set a fields value
     *
     * @param field field to retrieve
     * @param value value to set the field to
     *
     * @deprecated for use in JRecord, use {@link AbstractLine#getFieldValue(IFieldDetail)}.set(..)
     */
    public abstract void setField(IFieldDetail field, Object value);

    /**
     * Set the field with a Text value - ie update the field
     * without using any formatting
     *
     * @param recordIdx record layout
     * @param fieldIdx field number in the record
     * @param value new value
     *
     */
    public abstract void setFieldText(final int recordIdx, final int fieldIdx,
            String value);

    /**
     * Set a field to a Hex value
     * @param recordIdx record index
     * @param fieldIdx field index
     * @param val hex value
     */
    public abstract String setFieldHex(final int recordIdx, final int fieldIdx,
            String val);

//     was RecordEditor related but The RecordEditor has its own AbstractLine
//    
//    /**
//     * Test if Tree rebuild is required
//     */
//    public abstract boolean isRebuildTreeRequired();

    /**
     * Get all fields for a Record (by name)
     * @param recordName name of the Record
     * @return iterator over fields
     */
    public abstract FieldIterator getFieldIterator(String recordName);

    /**
     * Get all fields for a Record (by record-index)
     * @param recordNumber index of the Record
     * @return iterator over fields
     */
    public abstract FieldIterator getFieldIterator(int recordNumber);

    /**
     * Check if a field is defined in the record (or line).
     * <br>For Csv / Xml files, the field must exist in the "record"
     * <br>For Fixed width files<ul>
     *   <li>The record must be long enough to hold the field
     *   <li>The field value must not be hex zero's. This creates a potential problem with
     * Comp fields.
     * </ul>  
     * 
     * @param rec Record Index
     * @param fldNum Field Index
     * 
     * @return wether the field exists in the line
     */
    public boolean isDefined(int rec, int fldNum);

    
    /**
    * Check if a field is defined in the record (or line).
     * <br>For Csv / Xml files, the field must exist in the "record"
     * <br>For Fixed width files<ul>
     *   <li>The record must be long enough to hold the field
     *   <li>The field value must not be hex zero's. This creates a potential problem with
     * Comp fields.
     * </ul>  
     * 
     * @param fld field-defi9ni9tion
     * @return wether the field exists in the line
     */
    public boolean isDefined(IFieldDetail fld);
}